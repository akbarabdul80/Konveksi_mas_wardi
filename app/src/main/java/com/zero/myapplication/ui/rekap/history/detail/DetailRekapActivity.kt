package com.zero.myapplication.ui.rekap.history.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.zero.myapplication.data.model.user.DataRekap
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.databinding.ActivityDetailRekapBinding
import com.zero.myapplication.ui.rekap.RekapViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailRekapActivity : AppCompatActivity() {

    private val binding: ActivityDetailRekapBinding by viewBinding()
    private val viewModel: RekapViewModel by viewModel()
    lateinit var adapter: TabDetailAdapter
    private lateinit var dataRekap: DataRekap
    var data = ArrayList<String>()
    var dataResult: MutableList<DataResult> = mutableListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dataRekap = intent.getParcelableExtra("data")!!
        adapter = TabDetailAdapter(this, listOf("User", "Client"), dataRekap.id_rekap!!)

        with(binding) {
            tvDate.text = "${dataRekap.start_date} - ${dataRekap.end_date}"
            tvTotal.text = "${dataRekap.qty} Baju"
            data.addAll(listOf("User", "Client"))

            viewPager.adapter = adapter

            TabLayoutMediator(tbType, viewPager) { tab, position ->
                tab.text = data[position]
            }.attach()
        }

    }

    fun back(view: View) {
        finish()
    }
}