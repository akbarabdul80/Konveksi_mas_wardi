package com.zero.myapplication.ui.rekap.history.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabDetailAdapter(
    fragmentActivity: FragmentActivity,
    val data: List<Any>,
    val idRekap: Int
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        return DetailRekapFragment.newInstance(position, idRekap)
    }

}