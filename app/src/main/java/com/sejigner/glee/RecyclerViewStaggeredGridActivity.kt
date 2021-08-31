package com.sejigner.glee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sejigner.glee.adapter.WorkListStaggeredAdapter
import com.sejigner.glee.fragment.FragmentShare
import com.sejigner.glee.model.WorkModel
import com.sejigner.glee.ui.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_share.*

class RecyclerViewStaggeredGridActivity : FragmentShare() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        initView()
        return inflater.inflate(R.layout.fragment_share, container, false)
    }
    private fun initView() {
        rv_work.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //This will for default android divider
        rv_work.addItemDecoration(GridItemDecoration(10, 2))

        val workListAdapter = WorkListStaggeredAdapter()
        rv_work.adapter = workListAdapter
        workListAdapter.setWorkList(generateDummyData())
    }

    private fun generateDummyData(): List<WorkModel> {
        val listOfWork = mutableListOf<WorkModel>()

        var workModel = WorkModel("지멘", 128, R.drawable.girl, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("지멘", 128, R.drawable.girl, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("뮌스터", 128, R.drawable.girl, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("뮌스터", 298, R.drawable.girl, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("뮌스터", 298, R.drawable.girl, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        workModel = WorkModel("뮌스터", 298, R.drawable.girl, R.drawable.ic_my_page_selected)
        listOfWork.add(workModel)

        return listOfWork
    }
}