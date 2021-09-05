package com.secondslot.seloustev.mainscreen.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.secondslot.seloustev.R
import com.secondslot.seloustev.mainscreen.adapter.PictureViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mPictureViewPagerAdapter: PictureViewPagerAdapter
    private lateinit var mViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPictureViewPagerAdapter = PictureViewPagerAdapter(this)
        mViewPager = findViewById(R.id.view_pager)
        mViewPager.adapter = mPictureViewPagerAdapter

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val tabsList = listOf(
            applicationContext.resources.getString(R.string.latest),
            applicationContext.resources.getString(R.string.hot),
            applicationContext.resources.getString(R.string.top))
        TabLayoutMediator(tabLayout, mViewPager) { tab, position ->
            tab.text = tabsList[position]
        }.attach()

    }

}