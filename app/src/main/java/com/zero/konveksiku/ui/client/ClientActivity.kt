package com.zero.konveksiku.ui.client

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.konveksiku.R
import com.zero.konveksiku.data.model.user.DataClient
import com.zero.konveksiku.databinding.ActivityClientBinding
import com.zero.konveksiku.ui.bottom_action.BottomActionFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ClientActivity : AppCompatActivity(), BottomSheet, BottomActionFragment.BottomAction {

    private val binding: ActivityClientBinding by viewBinding()
    private val viewModel: ClientViewModel by viewModel()
    private val adapter: ClientAdapter = ClientAdapter {
        BottomActionFragment.newInstance(it).show(supportFragmentManager, "Bottom Action")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            rvData.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(this@ClientActivity)
            }

            fab.onClick {
                ClientBottomFragment.newInstance().show(supportFragmentManager, "Bottom Client")
            }
        }

        initLinstener()
    }

    private fun initLinstener() {
        viewModel.listenResult().observe(this) {
            adapter.submitData(it)
        }
    }

    fun back(view: View) {
        finish()
    }

    override fun onSubmit(data: DataClient) {
        viewModel.addClient(data)
    }

    override fun onUpdate(data: DataClient) {
        viewModel.updateClient(data)
    }


    override fun <T> onUpdate(data: T) {
        ClientBottomFragment.newInstance(data as DataClient)
            .show(supportFragmentManager, "Bottom Client")
    }

    override fun <T> onDelete(data: T) {
        MaterialAlertDialogBuilder(baseContext)
            .setTitle("Menghapus Client?")
            .setMessage("Jika anda menghapus client maka semua data termasuk rekap akan terhapus juga!")
            .setNegativeButton(resources.getString(R.string.title_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.title_yes)) { _, _ ->
                viewModel.deleteClient(data as DataClient)
                toast("Data Berhasil dihapus")
            }
            .show()
    }
}