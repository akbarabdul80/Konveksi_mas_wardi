package com.zero.myapplication.ui.rekap

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.zero.myapplication.R
import com.zero.myapplication.databinding.ActivityRekapBinding

class RekapActivity : AppCompatActivity() {

    lateinit var adapter: TabAdaper
    private val binding: ActivityRekapBinding by viewBinding()
    var data = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rekap)

        adapter = TabAdaper(this, listOf("User", "Client"))

        with(binding){

            data.add("User")
            data.add("Client")

            viewPager.adapter = adapter

            TabLayoutMediator(tbType, viewPager) { tab, position ->
                tab.text = data[position]
            }.attach()
        }

    }

    fun back(view: View) {}
}