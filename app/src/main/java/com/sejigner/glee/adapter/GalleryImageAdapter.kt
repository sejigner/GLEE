package com.sejigner.glee.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sejigner.glee.GlideApp
import com.sejigner.glee.R
import com.sejigner.glee.model.UserWork
import kotlinx.android.synthetic.main.item_gallery_image.view.*

class GalleryImageAdapter(private val itemList: List<UserWork>) : RecyclerView.Adapter<GalleryImageAdapter.ViewHolder>() {
    private var context: Context? = null
    var listener: GalleryImageClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryImageAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_gallery_image, parent,
            false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
    override fun onBindViewHolder(holder: GalleryImageAdapter.ViewHolder, position: Int) {
        holder.bind()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val image = itemList.get(adapterPosition)
            // load image
            GlideApp.with(context!!)
                .load(image.contentUri)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.ivGalleryImage)
            // adding click or tap handler for our image layout
            itemView.setOnClickListener {
                listener?.onClick(adapterPosition)
            }
        }
    }
}