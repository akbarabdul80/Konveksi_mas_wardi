package com.zero.myapplication.ui.rekap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.zero.myapplication.R
import com.zero.myapplication.databinding.ActivityRekapBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RekapActivity : AppCompatActivity() {

    lateinit var adapter: TabAdapter
    private val binding: ActivityRekapBinding by viewBinding()
    private val viewModel: RekapViewModel by viewModel()
    var data = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rekap)

        adapter = TabAdapter(this, listOf("User", "Client"))

        with(binding) {

            data.add("User")
            data.add("Client")

            viewPager.adapter = adapter

            TabLayoutMediator(tbType, viewPager) { tab, position ->
                tab.text = data[position]
            }.attach()
        }

        initListener()

    }

    @SuppressLint("SetTextI18n")
    private fun initListener() {
        viewModel.listenResultQty().observe(this, {
            binding.tvTotal.text = "${it.all_qty} Baju"
        })
    }

    fun back(view: View) {
        finish()
    }
}