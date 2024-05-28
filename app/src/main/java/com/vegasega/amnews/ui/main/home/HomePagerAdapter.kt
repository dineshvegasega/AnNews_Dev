package com.vegasega.amnews.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter (fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity, ) {

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LeftHome()
            1 -> CenterHome()
            2 -> RightHome()
            else -> CenterHome()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}