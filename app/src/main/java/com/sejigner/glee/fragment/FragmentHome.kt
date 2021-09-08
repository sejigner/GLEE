package com.sejigner.glee.fragment

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sejigner.glee.CanvasActivity
import com.sejigner.glee.EditTextActivity
import com.sejigner.glee.R
import com.sejigner.glee.model.SampleWorkModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_work.view.*


class FragmentHome : Fragment() {

    companion object {
        const val TITLE = "TITLE"
        const val AUTHOR = "AUTHOR"
        const val CONTENT = "CONTENT"
    }

    private lateinit var rbCafe24: TextView
    private lateinit var rbAritaBuri: TextView
    private lateinit var rbMapoFlowerIsland: TextView
    private lateinit var rbHambakSnow: TextView
    private lateinit var tvNewTranscription: TextView
    var participation_1: Int = 0
    private lateinit var groupAdapter: GroupAdapter<GroupieViewHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sample1 = SampleWorkModel("별 헤는 밤", "윤동주", participation_1, resources.getString(R.string.work_sample_content_1).length,resources.getString(R.string.work_sample_content_1))
        val sample2 = SampleWorkModel("별 헤는 밤", "윤동주", participation_1, 468, resources.getString(R.string.work_sample_preview_1))
        val sample3 = SampleWorkModel("별 헤는 밤", "윤동주", participation_1, 468, resources.getString(R.string.work_sample_preview_1))
        val sample4 = SampleWorkModel("별 헤는 밤", "윤동주", participation_1, 468, resources.getString(R.string.work_sample_preview_1))

        val adapter = GroupAdapter<GroupieViewHolder>()
        adapter.add(SampleWork(sample1))
        adapter.add(SampleWork(sample2))
        adapter.add(SampleWork(sample3))
        adapter.add(SampleWork(sample4))
        val linearLayoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)



        adapter.setOnItemClickListener { item, view ->

            val workItem = item as SampleWork

            val intent = Intent(requireActivity(), CanvasActivity::class.java)

            intent.putExtra(TITLE, workItem.title )
            intent.putExtra(AUTHOR, workItem.author)
            intent.putExtra(CONTENT, workItem.content)
            startActivity(intent)
        }

        // 부모 뷰 터치 가로채기 방지
        rv_work_preview.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {

            override fun onTouchEvent(view: RecyclerView, event: MotionEvent) {}

            override fun onInterceptTouchEvent(view: RecyclerView, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        rv_work_preview.parent?.requestDisallowInterceptTouchEvent(true)
                    }
                }
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        rv_work_preview.layoutManager = linearLayoutManager
        rv_work_preview.adapter = adapter


        rbCafe24 = view.findViewById(R.id.rb_cafe24SurroundAir)
        rbAritaBuri = view.findViewById(R.id.rb_aritaBuri)
        rbMapoFlowerIsland = view.findViewById(R.id.rb_mapoFlowerIsland)
        rbHambakSnow = view.findViewById(R.id.rb_hambaksnow)
        tvNewTranscription = view.findViewById(R.id.tv_new_transcription)

        rbCafe24.setOnClickListener {
            rv_work_preview.tv_work_content.typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/cafe24_surround_air.ttf")
        }
        rbAritaBuri.setOnClickListener {
            rv_work_preview.tv_work_content.typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/arita_buri.otf")
        }
        rbMapoFlowerIsland.setOnClickListener {
            rv_work_preview.tv_work_content.typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/mapo_flower_island.ttf")
        }

        rbHambakSnow.setOnClickListener {
            rv_work_preview.tv_work_content.typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/hambaksnow.ttf")
        }

        tvNewTranscription.setOnClickListener {
            val intent = Intent(activity, EditTextActivity::class.java)
            startActivity(intent)
        }
    }
}

class SampleWork(val sampleWork: SampleWorkModel) :
    Item<GroupieViewHolder>() {

    val title = sampleWork.title
    val author = sampleWork.author
    val content = sampleWork.content

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tv_work_title.text = sampleWork.title
            tv_work_author.text = sampleWork.author
            tv_work_character_number.text = sampleWork.characterNumber.toString()+ "자"
            tv_work_participation.text = sampleWork.participationNumber.toString() + resources.getString(R.string.participation_number)
            tv_work_content.text =sampleWork.content
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_work
    }
}