package com.sejigner.glee.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sejigner.glee.*
import com.sejigner.glee.adapter.WorkListMyPageStaggeredAdapter
import com.sejigner.glee.model.WorkModelMyPage
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_share.*

class FragmentMyPage : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImages()

        hs_major_works.parent.requestDisallowInterceptTouchEvent(false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun setImages() {
        GlideApp.with(this).load(R.drawable.work_sample_my_page).into(iv_major_work_sample_1)
        GlideApp.with(this).load(R.drawable.work_sample_my_page2).into(iv_major_work_sample_2)
        GlideApp.with(this).load(R.drawable.user_profile_sample).circleCrop().into(iv_user_my_page)
    }

    private fun initView() {

        rv_recent_work_my_page.layoutManager = StaggeredGridLayoutManager(2, 1).apply {
            gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            orientation = StaggeredGridLayoutManager.VERTICAL
        }
        rv_recent_work_my_page.setHasFixedSize(false)

        //rv_recent_work_my_page.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //This will for default android divider
        //rv_recent_work_my_page.addItemDecoration(GridItemDecoration(10, 2))

        val workListAdapter = WorkListMyPageStaggeredAdapter()
        rv_recent_work_my_page.adapter = workListAdapter
        workListAdapter.setWorkList(generateDummyData())
    }


    private fun generateDummyData(): List<WorkModelMyPage> {
        val listOfCurrentWork = mutableListOf<WorkModelMyPage>()

        var workModelMyPage = WorkModelMyPage(52, R.drawable.current_work_sample_my_page1)
        listOfCurrentWork.add(workModelMyPage)

        workModelMyPage = WorkModelMyPage(28, R.drawable.current_work_sample_my_page2)
        listOfCurrentWork.add(workModelMyPage)

        workModelMyPage = WorkModelMyPage(35, R.drawable.current_work_sample_my_page3)
        listOfCurrentWork.add(workModelMyPage)

        workModelMyPage = WorkModelMyPage(40, R.drawable.work_exam2)
        listOfCurrentWork.add(workModelMyPage)


        return listOfCurrentWork
    }

}