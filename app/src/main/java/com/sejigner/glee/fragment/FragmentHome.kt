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
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import androidx.viewpager2.widget.ViewPager2
import com.sejigner.glee.CanvasActivity
import com.sejigner.glee.EditTextActivity
import com.sejigner.glee.R
import com.sejigner.glee.model.SampleWorkModel
import com.sejigner.glee.ui.SampleWorkItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*
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

        val sample1 = SampleWorkModel("별 헤는 밤", "윤동주", participation_1, 468, resources.getString(R.string.work_sample_preview_1))
        val sample2 = SampleWorkModel("별 헤는 밤", "윤동주", participation_1, 468, resources.getString(R.string.work_sample_preview_1))
        val sample3 = SampleWorkModel("별 헤는 밤", "윤동주", participation_1, 468, resources.getString(R.string.work_sample_preview_1))
        val sample4 = SampleWorkModel("별 헤는 밤", "윤동주", participation_1, 468, resources.getString(R.string.work_sample_preview_1))


        var workList: List<SampleWorkModel> = listOf(sample1, sample2, sample3, sample4)

        groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(workList.toSampleWorkITem())
        }

        groupAdapter.setOnItemClickListener { item, view ->

            val workItem = item as SampleWorkItem

            val intent = Intent(requireActivity(), CanvasActivity::class.java)
            intent.putExtra(TITLE, workItem)
            intent.putExtra(AUTHOR, workItem.author)
            intent.putExtra(CONTENT, workItem.content)
            startActivity(intent)
        }

        rv_work_preview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = groupAdapter
        }

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


    private fun List<SampleWorkModel>.toSampleWorkITem(): List<SampleWorkItem> {
        return this.map {
            SampleWorkItem(it)
        }
    }
}