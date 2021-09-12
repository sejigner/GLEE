package com.sejigner.glee.fragment

import android.content.ContentUris
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.database.ContentObserver
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sejigner.glee.adapter.SharedWorkAdapter
import com.sejigner.glee.databinding.FragmentShareBinding
import com.sejigner.glee.model.UserWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val GLEE = "Glee"

class FragmentShare : Fragment() {

    private lateinit var imageList : ArrayList<UserWork>
    private lateinit var contentObserver: ContentObserver
    private lateinit var binding: FragmentShareBinding
    private lateinit var externalStoragePhotoAdapter: SharedWorkAdapter
    private var readPermissionGranted = false
    private var writePermissionGranted = false
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {

        binding = FragmentShareBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageList = arrayListOf()
        externalStoragePhotoAdapter = SharedWorkAdapter()
        setUpExternalStorageRecyclerView()
        initContentObserver()
        loadPhotosFromExternalStorageIntoRecyclerView()

        

//        // init adapter
//        galleryAdapter = GalleryImageAdapter(imageList)
//        galleryAdapter.listener = this
//        // init recyclerview
//        recyclerView.layoutManager = GridLayoutManager(requireActivity(), SPAN_COUNT)
//        recyclerView.adapter = galleryAdapter
//        // load images
//        loadPhotosFromExternalStorageIntoRecyclerView()

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            permissions -> readPermissionGranted = permissions[android.Manifest.permission.READ_EXTERNAL_STORAGE]?: readPermissionGranted

            if(readPermissionGranted) {
                loadPhotosFromExternalStorageIntoRecyclerView()
            } else {
                Toast.makeText(requireActivity(),"작품을 둘러보려면 읽기 권한을 허용해주세요.",Toast.LENGTH_LONG).show()
            }
        }
        updateOrRequestPermissions()

    }

    private fun setUpExternalStorageRecyclerView() = binding.recyclerView.apply {
        adapter = externalStoragePhotoAdapter
        layoutManager = StaggeredGridLayoutManager(5, RecyclerView.VERTICAL)

        externalStoragePhotoAdapter.onItemClick = { position ->
            onClick(position)

        }
    }

    private fun updateOrRequestPermissions() {
        val hasReadPermission = ContextCompat.checkSelfPermission(
            requireActivity(),
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        val hasWritePermission = ContextCompat.checkSelfPermission(
            requireActivity(),
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

        readPermissionGranted = hasReadPermission
        writePermissionGranted = hasWritePermission

        val permissionsToRequest = mutableListOf<String>()
        if(!writePermissionGranted) {
            permissionsToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!readPermissionGranted) {
            permissionsToRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if(permissionsToRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }

    private fun onClick(position : Int) {
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

            val photos = getData()
            if(!photos.isNullOrEmpty()) {
                imageList.clear()
                imageList.addAll(photos)
            }
            externalStoragePhotoAdapter.submitList(photos)
        }
    }

    private suspend fun getData(): List<UserWork>? =

        withContext(Dispatchers.IO) {
            try {

                val collection = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
                } else MediaStore.Images.Media.EXTERNAL_CONTENT_URI

                val selection =
                    MediaStore.Images.ImageColumns.RELATIVE_PATH + " like ? "
                val projection = arrayOf(
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.RELATIVE_PATH,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                    MediaStore.Images.Media.BUCKET_ID,
                    MediaStore.MediaColumns.WIDTH
                )

                val selectionArgs = arrayOf("%$GLEE%")

                val sortOrder = MediaStore.MediaColumns.DATE_ADDED + " COLLATE NOCASE DESC"

                val itemList: MutableList<UserWork> = mutableListOf()

                context?.contentResolver?.query(
                    collection,
                    projection,
                    selection,
                    selectionArgs,
                    sortOrder
                )?.use { cursor ->

                    val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                    val displayNameColumn =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
//                    val relativePathColumn =
//                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.RELATIVE_PATH)
//                    val widthPathColumn =
//                        cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.WIDTH)


                    while (cursor.moveToNext()) {
                        val id = cursor.getLong(idColumn)
                        val displayName = cursor.getString(displayNameColumn)
//                        val relativePath = cursor.getString(relativePathColumn)
//                        val width = cursor.getInt(widthPathColumn)


                        val contentUri = ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            id
                        )

                        itemList.add(UserWork(id, displayName, contentUri))

                    }
                    cursor.close()
                }

                itemList
            } catch (e: Exception) {
                Log.d(
                    "MediaStoreException", "The exception for getData is " +
                            "$e"
                )
                null
            }

        }

    private suspend fun loadPhotosFromExternalStorage(): List<UserWork> {
        return withContext(Dispatchers.IO) {
            val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val path = "Pictures/TestApp%"
            val selection = "${MediaStore.MediaColumns.RELATIVE_PATH} LIKE ?"
            val selectionargs = arrayOf(path)

            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME
            )
            val photos = mutableListOf<UserWork>()

            requireActivity().contentResolver.query(
                collection,
                projection,
                selection,
                selectionargs,
                "${MediaStore.Images.Media.DISPLAY_NAME} DESC"
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)


                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val displayName = cursor.getString(displayNameColumn)
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