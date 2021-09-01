package com.sejigner.glee.ui

import com.sejigner.glee.R
import com.sejigner.glee.model.SampleWorkModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_work.view.*

class SampleWorkItem(private val sampleWork : SampleWorkModel) : Item()  {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tv_work_title.text = sampleWork.title
            tv_work_author.text = sampleWork.author
            tv_work_character_number.text = sampleWork.characterNumber.toString()+ "자"
            tv_work_participation.text = sampleWork.participationNumber.toString() + resources.getString(R.string.participation_number)
            tv_work_content.text =sampleWork.content
        }
    }


    override fun getLayout() = R.layout.item_work
}