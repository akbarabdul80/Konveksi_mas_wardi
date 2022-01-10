package com.zero.myapplication.ui.rekap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.myapplication.data.model.user.DataRekap
import com.zero.myapplication.data.model.user.DataRekapResult
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.databinding.ActivityRekapBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RekapActivity : AppCompatActivity() {

    lateinit var adapter: TabAdapter
    private val binding: ActivityRekapBinding by viewBinding()
    private val viewModel: RekapViewModel by viewModel()
    var data = ArrayList<String>()
    var dataResult: MutableList<DataResult> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = TabAdapter(this, listOf("User", "Client"))

        with(binding) {

            data.addAll(listOf("User", "Client"))

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

            binding.fab.onClick {
                if (it.all_qty == 0) {
                    toast("Tidak ada data yang bisa direkap")
                } else {

                    val dataRekapResult: MutableList<DataRekapResult> = mutableListOf()
                    val dataRekap = DataRekap(
                        dataResult[0].date,
                        dataResult[dataResult.size - 1].date,
                        it.all_qty
                    )
                    dataResult.forEach { result ->
                        dataRekapResult.add(
                            DataRekapResult(
                                1,
                                result.id_result!!
                            )
                        )
                    }

                    viewModel.addRekap(dataRekap, dataRekapResult)
                }
            }
        })

        viewModel.listenResultNow().observe(this, {
            dataResult.clear()
            dataResult.addAll(it)
        })
    }

    fun back(view: View) {
        finish()
    }
}