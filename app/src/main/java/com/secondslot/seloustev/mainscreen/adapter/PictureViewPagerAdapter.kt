package com.secondslot.seloustev.mainscreen.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.secondslot.seloustev.mainscreen.ui.PictureFragment

class PictureViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): PictureFragment {
        return PictureFragment.newInstance(position)
    }

    override fun getItemCount(): Int = 3
}