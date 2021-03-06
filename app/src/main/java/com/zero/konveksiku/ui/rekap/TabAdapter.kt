package com.zero.konveksiku.ui.rekap

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(
    fragmentActivity: FragmentActivity,
    val data: List<Any>,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        return RekapFragment.newInstance(position)
    }

}