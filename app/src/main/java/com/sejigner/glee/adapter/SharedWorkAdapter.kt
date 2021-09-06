package com.sejigner.glee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sejigner.glee.databinding.ItemPhotoBinding
import com.sejigner.glee.model.UserWork
import javax.inject.Inject

class SharedWorkAdapter @Inject constructor() : ListAdapter<UserWork, SharedWorkAdapter.WorkViewHolder>(Companion) {
    var onItemClick : ((Int)-> Unit)? = null
    var userWorks : List<UserWork> = emptyList()

    inner class WorkViewHolder(val binding : ItemPhotoBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }
    }

    companion object : DiffUtil.ItemCallback<UserWork>() {
        override fun areItemsTheSame(oldItem: UserWork, newItem: UserWork): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserWork, newItem: UserWork): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : WorkViewHolder {
        return WorkViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        val photo = currentList[position]
        holder.binding.apply {
            ivPhoto.setImageURI(photo.contentUri)
            ConstraintSet().apply {
                clone(root)
                applyTo(root)
            }
        }
    }
}