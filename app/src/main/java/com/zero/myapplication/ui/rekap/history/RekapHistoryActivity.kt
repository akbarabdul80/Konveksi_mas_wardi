package com.zero.myapplication.ui.rekap.history

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.startActivity
import com.zero.myapplication.databinding.ActivityRekapHistoryBinding
import com.zero.myapplication.ui.rekap.RekapViewModel
import com.zero.myapplication.ui.rekap.history.detail.DetailRekapActivity
import org.koin.android.viewmodel.ext.android.viewModel

class RekapHistoryActivity : AppCompatActivity() {

    val binding: ActivityRekapHistoryBinding by viewBinding()
    private val viewModel: RekapViewModel by viewModel()
    private val adapter: RekapHistoryAdapter by lazy {
        RekapHistoryAdapter {
            startActivity(DetailRekapActivity::class.java) { intent ->
                intent.putExtra("data", it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            rvData.also {
                rvData.adapter = adapter
                rvData.layoutManager = LinearLayoutManager(this@RekapHistoryActivity)
            }
        }

        initListener()
    }

    private fun initListener() {
        viewModel.listenRekap().observe(this, {
            adapter.submitData(it)
        })
    }

    fun back(view: View) {
        finish()
    }
}