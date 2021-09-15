package com.sejigner.glee.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sejigner.glee.EditTextActivity
import com.sejigner.glee.R
import com.sejigner.glee.fragment.FontClickListener
import com.sejigner.glee.model.SampleWorkModel
import kotlinx.android.synthetic.main.item_work.view.*

class SampleWorkAdapter(private val context : Context) : RecyclerView.Adapter<SampleWorkAdapter.ViewHolder>() {
    var listOfWorks = mutableListOf<SampleWorkModel>()

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }



    private lateinit var itemClickListener: ItemClickListener


    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_work,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listOfWorks.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listOfWorks[position])

        viewHolder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), FontClickListener {

//        tv_work_title.text = sampleWork.title
//        tv_work_author.text = sampleWork.author
//        tv_work_character_number.text = sampleWork.characterNumber.toString() + "자"
//        tv_work_participation.text =
//        sampleWork.participationNumber.toString() + resources.getString(R.string.participation_number)
//        tv_work_content.text = sampleWork.content

        private val title = itemView.findViewById<TextView>(R.id.tv_work_title)
        private val author = itemView.findViewById<TextView>(R.id.tv_work_author)
        private val characterNumber = itemView.findViewById<TextView>(R.id.tv_work_character_number)
        private val participation = itemView.findViewById<TextView>(R.id.tv_work_participation)
        private val content = itemView.findViewById<TextView>(R.id.tv_work_content)

        override fun onClickFont(font: String) {
            when(font) {
                EditTextActivity.CAFE24_SURROUND_AIR ->
                { content.typeface = Typeface.createFromAsset(itemView.context.assets, "fonts/cafe24_surround_air.ttf")}
                EditTextActivity.ARITA_BURI ->
                { content.typeface = Typeface.createFromAsset(itemView.context.assets, "fonts/arita_buri.otf")}
            }
        }

        fun bind(item: SampleWorkModel) {
            title.text = item.title
            author.text = item.author
            characterNumber.text = item.characterNumber.toString()
            participation.text = item.participationNumber.toString()
            content.text = item.content
        }



    }

}