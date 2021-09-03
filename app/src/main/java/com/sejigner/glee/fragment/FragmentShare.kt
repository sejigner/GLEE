package com.sejigner.glee.fragment

import android.content.ContentUris
import android.graphics.BitmapFactory
import android.media.MediaActionSound
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.database.ContentObserver
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.sejigner.glee.*
import com.sejigner.glee.adapter.GalleryImageAdapter
import com.sejigner.glee.adapter.GalleryImageClickListener
import com.sejigner.glee.model.UserWork
import kotlinx.android.synthetic.main.fragment_share.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


open class FragmentShare : Fragment(), GalleryImageClickListener {
    // gallery column count
    private val SPAN_COUNT = 3
    lateinit var galleryAdapter: GalleryImageAdapter
    private val IMAGE_LOADER_ID = 1
    private var imageList = ArrayList<UserWork>()
    private lateinit var contentObserver: ContentObserver

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {

        return inflater.inflate(R.layout.fragment_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // init adapter
        galleryAdapter = GalleryImageAdapter(imageList)
        galleryAdapter.listener = this
        // init recyclerview
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), SPAN_COUNT)
        recyclerView.adapter = galleryAdapter
        initContentObserver()
        // load images
        loadPhotosFromExternalStorageIntoRecyclerView()

    }

    override fun onClick(position: Int) {
        // handle click of image
        val bundle = Bundle()
        bundle.putSerializable("images", imageList)
        bundle.putInt("position", position)
        val fragmentTransaction = childFragmentManager.beginTransaction()
        val galleryFragment = GalleryFullscreenFragment()
        galleryFragment.arguments = bundle
        galleryFragment.show(fragmentTransaction, "gallery")
    }

    private fun loadPhotosFromExternalStorageIntoRecyclerView() {
        lifecycleScope.launch {

            val photos = loadPhotosFromExternalStorage()
            imageList = photos as ArrayList<UserWork>
        }
    }

    private suspend fun loadPhotosFromExternalStorage(): List<UserWork> {
        return withContext(Dispatchers.IO) {
            val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT
            )
            val photos = mutableListOf<UserWork>()

            requireActivity().contentResolver.query(
                collection,
                projection,
                null,
                null,
                "${MediaStore.Images.Media.DISPLAY_NAME} ASC"
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
                val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val displayName = cursor.getString(displayNameColumn)
                    val width = cursor.getInt(widthColumn)
                    val height = cursor.getInt(heightColumn)
                    val contentUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    photos.add(UserWork(id, displayName, contentUri))
                }
                photos.toList()
            } ?: listOf()
        }
    }

    private fun initContentObserver() {
        contentObserver = object : ContentObserver(null) {
            override fun onChange(selfChange: Boolean) {
                loadPhotosFromExternalStorageIntoRecyclerView()
            }
        }
        requireActivity().contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            contentObserver
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().contentResolver.unregisterContentObserver(contentObserver)
    }
}