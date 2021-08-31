package com.sejigner.glee.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sejigner.glee.GlideApp
import com.sejigner.glee.model.WorkModelMyPage
import kotlinx.android.synthetic.main.list_item_grid_work.view.*
import kotlinx.android.synthetic.main.list_item_grid_work_my_page.view.*
import java.util.*

class WorkListMyPageStaggeredViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(workModel: WorkModelMyPage) {
        itemView.tv_count_like_my_page.text = workModel.likeCount.toString()
        GlideApp.with(itemView.context).load(workModel.workPicture).into(itemView.iv_work_my_page)
        GlideApp.with(itemView.context).load(workModel.likeIcon).into(itemView.iv_like_my_page)
    }
}