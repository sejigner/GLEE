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
import com.sejigner.glee.adapter.SampleWorkAdapter
import com.sejigner.glee.model.SampleWorkModel
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

    private lateinit var sampleWorkAdapter: SampleWorkAdapter
    private lateinit var rbCafe24: TextView
    private lateinit var rbAritaBuri: TextView
    private lateinit var rbMapoFlowerIsland: TextView
    private lateinit var rbHambakSnow: TextView
    private lateinit var tvNewTranscription: TextView
    private var sample1 : SampleWorkModel ?= null
    private var sample2 : SampleWorkModel ?= null
    private var sample3 : SampleWorkModel ?= null
    private var sample4 : SampleWorkModel ?= null
    private var sample5 : SampleWorkModel ?= null
    private var sample6 : SampleWorkModel ?= null
    var textChoice = ""

    var participation_1: Int = 0

    val list = mutableListOf<SampleWorkModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
            : View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        val childCount = rv_work_preview.getChildCount()


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

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rv_work_preview.layoutManager = linearLayoutManager
        rv_work_preview.adapter = sampleWorkAdapter


        rbCafe24 = view.findViewById(R.id.rb_cafe24SurroundAir)
        rbMapoFlowerIsland = view.findViewById(R.id.rb_mapoFlowerIsland)
        rbHambakSnow = view.findViewById(R.id.rb_hambaksnow)
        tvNewTranscription = view.findViewById(R.id.tv_new_transcription)



        rbCafe24.setOnClickListener {
            sampleWorkAdapter.selectedFont = EditTextActivity.CAFE24_SURROUND_AIR
            sampleWorkAdapter.notifyDataSetChanged()

        }
        rbMapoFlowerIsland.setOnClickListener {
            sampleWorkAdapter.selectedFont = EditTextActivity.MAPO_FLOWER
            sampleWorkAdapter.notifyDataSetChanged()
        }

        rbHambakSnow.setOnClickListener {
            sampleWorkAdapter.selectedFont = EditTextActivity.HAMBAK_SNOW
            sampleWorkAdapter.notifyDataSetChanged()
        }

        rb_cafe24_shining_star.setOnClickListener {
            sampleWorkAdapter.selectedFont = EditTextActivity.CAFE24_SHINING_STAR
            sampleWorkAdapter.notifyDataSetChanged()
        }
        rb_middle_school_student.setOnClickListener {
            sampleWorkAdapter.selectedFont = EditTextActivity.MIDDLE_SCHOOL_STUDENT
            sampleWorkAdapter.notifyDataSetChanged()
        }
        rb_nanum_barun_pen.setOnClickListener {
            sampleWorkAdapter.selectedFont = EditTextActivity.NANUM_BARUN_PEN
            sampleWorkAdapter.notifyDataSetChanged()
        }
        rb_nanum_pen.setOnClickListener {
            sampleWorkAdapter.selectedFont = EditTextActivity.NANUM_PEN
            sampleWorkAdapter.notifyDataSetChanged()
        }
        rb_bm_euljiro.setOnClickListener {
            sampleWorkAdapter.selectedFont = EditTextActivity.BM_EUJIRO
            sampleWorkAdapter.notifyDataSetChanged()
        }





        tvNewTranscription.setOnClickListener {
            val intent = Intent(activity, EditTextActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        sample1 = SampleWorkModel(
            resources.getString(R.string.work_sample_title_1),
            resources.getString(R.string.work_sample_author_1),
            participation_1,
            resources.getString(R.string.work_sample_content_1).length,
            resources.getString(R.string.work_sample_content_1).substring(0..70) + "..."
        )
        sample2 = SampleWorkModel(
            resources.getString(R.string.work_sample_title_2),
            resources.getString(R.string.work_sample_author_2),
            participation_1,
            resources.getString(R.string.work_sample_content_2).length,
            resources.getString(R.string.work_sample_content_2).substring(0..60) + "..."
        )
        sample3 = SampleWorkModel(
            resources.getString(R.string.work_sample_title_3),
            resources.getString(R.string.work_sample_author_3),
            participation_1,
            resources.getString(R.string.work_sample_content_3).length,
            resources.getString(R.string.work_sample_content_3).substring(0..70) + "..."
        )
        sample4 = SampleWorkModel(
            resources.getString(R.string.work_sample_title_4),
            resources.getString(R.string.work_sample_author_4),
            participation_1,
            resources.getString(R.string.work_sample_content_4).length,
            resources.getString(R.string.work_sample_content_4).substring(0..70) + "..."
        )
        sample5 = SampleWorkModel(
            resources.getString(R.string.work_sample_title_5),
            resources.getString(R.string.work_sample_author_5),
            participation_1,
            resources.getString(R.string.work_sample_content_5).length,
            resources.getString(R.string.work_sample_content_5).substring(0..50) + "..."
        )

        sample6 = SampleWorkModel(
            resources.getString(R.string.work_sample_title_6),
            resources.getString(R.string.work_sample_author_6),
            participation_1,
            resources.getString(R.string.work_sample_content_6).length,
            resources.getString(R.string.work_sample_content_6).substring(0..50) + "..."
        )

        sampleWorkAdapter = SampleWorkAdapter(requireActivity())
        rv_work_preview.adapter = sampleWorkAdapter


        list.apply {
            add(sample1!!)
            add(sample2!!)
            add(sample3!!)
            add(sample4!!)
            add(sample5!!)
            add(sample6!!)

            sampleWorkAdapter.listOfWorks = list
            sampleWorkAdapter.notifyDataSetChanged()

        }

        sampleWorkAdapter.setItemClickListener( object : SampleWorkAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                val workItem = sampleWorkAdapter.listOfWorks[position]

                val intent = Intent(requireActivity(), CanvasActivity::class.java)

                intent.putExtra(TITLE, workItem.title)
                intent.putExtra(AUTHOR, workItem.author)
                intent.putExtra(CONTENT, workItem.content)
                startActivity(intent)
            }
        })

    }

    private fun initRecycler() {

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
            tv_work_character_number.text = sampleWork.characterNumber.toString() + "자"
            tv_work_participation.text =
                sampleWork.participationNumber.toString() + resources.getString(R.string.participation_number)
            tv_work_content.text = sampleWork.content
        }
    }


    override fun getLayout(): Int {
        return R.layout.item_work
    }
}