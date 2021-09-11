package com.sejigner.glee.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
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

        hs_major_works.parent.requestDisallowInterceptTouchEvent(false)

        iv_menu.setOnClickListener {
            val pop = PopupMenu(requireActivity(), it)

            pop.menuInflater.inflate(R.menu.my_page_popup_menu, pop.menu)
            pop.show()
            pop.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.item_license ->  {
                        val intent = Intent(requireActivity(),LicenseActivity::class.java)
                        startActivity(intent)
                    }


                    R.id.item_open_source -> {
                        val intent = Intent(requireActivity(),OpenSourceActivity::class.java)
                        startActivity(intent)
                    }

                }
                false
            }
            pop.show()
        }
    }
}