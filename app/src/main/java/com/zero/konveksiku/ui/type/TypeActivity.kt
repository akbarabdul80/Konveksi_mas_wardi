package com.zero.konveksiku.ui.type

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.konveksiku.R
import com.zero.konveksiku.data.model.user.DataType
import com.zero.konveksiku.databinding.ActivityTypeBinding
import com.zero.konveksiku.ui.bottom_action.BottomActionFragment
import org.koin.android.viewmodel.ext.android.viewModel

class TypeActivity : AppCompatActivity(), BottomSheet, BottomActionFragment.BottomAction {

    private val binding: ActivityTypeBinding by viewBinding()
    private val viewModel: TypeViewModel by viewModel()
    private val adapter: TypeAdapter = TypeAdapter {
        BottomActionFragment.newInstance(it).show(supportFragmentManager, "Bottom Action")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            rvData.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(this@TypeActivity)
            }

            fab.onClick {
                TypeBottomFragment.newInstance().show(supportFragmentManager, "Type User")
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

    override fun onSubmit(data: DataType) {
        viewModel.addType(data)
    }

    override fun onUpdate(data: DataType) {
        viewModel.updateType(data)
    }

    override fun <T> onUpdate(data: T) {
        TypeBottomFragment.newInstance(data as DataType)
            .show(supportFragmentManager, "Bottom Type")
    }

    override fun <T> onDelete(data: T) {
        MaterialAlertDialogBuilder(baseContext)
            .setTitle("Menghapus Type?")
            .setMessage("Jika anda menghapus type maka semua data termasuk rekap akan terhapus juga!")
            .setNegativeButton(resources.getString(R.string.title_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.title_yes)) { _, _ ->
                viewModel.deleteType(data as DataType)
                toast("Data Berhasil dihapus")
            }
            .show()
    }
}