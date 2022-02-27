package com.zero.konveksiku.ui.rekap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.konveksiku.R
import com.zero.konveksiku.data.model.user.DataRekap
import com.zero.konveksiku.data.model.user.DataRekapResult
import com.zero.konveksiku.data.model.user.DataResult
import com.zero.konveksiku.databinding.ActivityRekapBinding
import com.zero.konveksiku.ui.rekap.history.RekapHistoryActivity
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

            btnRekap.onClick {
                startActivity(RekapHistoryActivity::class.java)
            }
        }

        initListener()

    }

    @SuppressLint("SetTextI18n")
    private fun initListener() {
        viewModel.listenResultQty().observe(this) {
            binding.tvTotal.text = "${it.all_qty} Baju"

            binding.fab.onClick {
                if (it.all_qty == 0) {
                    toast("Tidak ada data yang bisa direkap")
                } else {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Melakukan Rekap?")
                        .setMessage("Anda akan merekap semua hasil pekerjaan yang ada disini")
                        .setNegativeButton(resources.getString(R.string.title_cancel)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton(resources.getString(R.string.title_yes)) { _, _ ->
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
                        .show()
                }
            }
        }

        viewModel.listenResultNow().observe(this) {
            dataResult.clear()
            dataResult.addAll(it)
        }
    }

    fun back(view: View) {
        finish()
    }
}