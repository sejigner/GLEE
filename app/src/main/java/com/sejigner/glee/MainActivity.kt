package com.sejigner.glee

import android.Manifest
import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.sejigner.glee.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private val fragmentHome by lazy { FragmentHome() }
    private val fragmentChat by lazy { FragmentShare() }
    private val fragmentMyPage by lazy { FragmentMyPage() }

    private val fragments : List<Fragment> = listOf(fragmentHome, fragmentChat, fragmentMyPage)

    private val pagerAdapter: MainViewPagerAdapter by lazy { MainViewPagerAdapter(this, fragments) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager()
        initNavigationBar()
        bnv_main.itemIconTintList = null
        // transparentStatusAndNavigation()
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
    }

    private fun initNavigationBar() {
        bnv_main.run {
            setOnNavigationItemSelectedListener {
                val page = when(it.itemId) {
                    R.id.home -> 0
                    R.id.share -> 1
                    R.id.my_page -> 2
                    else -> 0
                }

                if (page!=vp_main.currentItem) {
                    vp_main.currentItem = page
                }

                true
            }
            selectedItemId = R.id.home
        }
    }

    private fun initViewPager() {
        vp_main.run {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val navigation = when(position) {
                        0 -> R.id.home
                        1 -> R.id.share
                        2 -> R.id.my_page
                        else -> R.id.home
                    }

                    if(bnv_main.selectedItemId != navigation) {
                        bnv_main.selectedItemId = navigation
                    }
                }
            })
        }
    }
}