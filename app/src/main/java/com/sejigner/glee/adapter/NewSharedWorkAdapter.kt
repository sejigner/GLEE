package com.sejigner.glee.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sejigner.glee.model.UserWork
import javax.inject.Inject

class NewSharedWorkAdapter @Inject constructor() : RecyclerView.Adapter<NewSharedWorkAdapter.ViewHolder>(){

    var onItemClick : ((UserWork )-> Unit)? = null
    var userWorks : List<UserWork> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewSharedWorkAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NewSharedWorkAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(userWorks[adapterPosition])
            }
        }
    }


}