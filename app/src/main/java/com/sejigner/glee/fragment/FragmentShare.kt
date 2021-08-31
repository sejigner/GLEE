package com.sejigner.glee.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sejigner.glee.*
import com.sejigner.glee.adapter.WorkListStaggeredAdapter
import com.sejigner.glee.model.WorkModel
import com.sejigner.glee.ui.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_share.*

open class FragmentShare : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        return inflater.inflate(R.layout.fragment_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        setImageSample()
    }

    private fun setImageSample() {
        GlideApp.with(this).load(R.drawable.work_exam).into(iv_work_exam_1)
        GlideApp.with(this).load(R.drawable.work_exam2).into(iv_work_exam_2)
        GlideApp.with(this).load(R.drawable.sample_user_1).circleCrop().into(iv_sample_user_picture_1)
        GlideApp.with(this).load(R.drawable.sample_user_2).circleCrop().into(iv_sample_user_picture_2)
    }

    private fun initView() {
/*
        rv_work.layoutManager = StaggeredGridLayoutManager(2, 1).apply {
            gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            orientation = StaggeredGridLayoutManager.VERTICAL
        }
        rv_work.setHasFixedSize(false)
*/
        //This will for default android divider
        rv_work.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_work.addItemDecoration(GridItemDecoration(10, 2))
        rv_work.layoutManager
        val workListAdapter = WorkListStaggeredAdapter()
        rv_work.adapter = workListAdapter
        workListAdapter.setWorkList(generateDummyData())
    }

    private fun generateDummyData(): List<WorkModel> {
        val listOfWork = mutableListOf<WorkModel>()

        var workModel = WorkModel("지멘", 128, R.drawable.exam_1, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("보석", 11, R.drawable.exam_2, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("배준수", 29, R.drawable.exam_3, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("흰둥", 98, R.drawable.exam_4, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("여의도", 30, R.drawable.exam_5, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("혜승", 56, R.drawable.current_work_sample_my_page2, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)
        workModel = WorkModel("성북", 7, R.drawable.work_sample_my_page2, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)



        return listOfWork
    }



}