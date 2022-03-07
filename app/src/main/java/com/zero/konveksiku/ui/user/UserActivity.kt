package com.zero.konveksiku.ui.user

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.konveksiku.R
import com.zero.konveksiku.data.model.user.DataResult
import com.zero.konveksiku.data.model.user.DataUser
import com.zero.konveksiku.databinding.ActivityUserBinding
import com.zero.konveksiku.ui.bottom_action.BottomActionFragment
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class UserActivity : AppCompatActivity(), BottomSheet, BottomActionFragment.BottomAction {

    private val binding: ActivityUserBinding by viewBinding()
    private val viewModel: UserViewModel by viewModel()
    private val adapter: UserAdapter = UserAdapter {
        BottomActionFragment.newInstance(it).show(supportFragmentManager, "Bottom Action")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            rvData.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(this@UserActivity)
            }

            fab.onClick {
                UserBottomFragment.newInstance().show(supportFragmentManager, "Bottom User")
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

    override fun onSubmit(data: DataUser) {
        viewModel.addUser(data)
    }

    override fun onUpdate(data: DataUser) {
        viewModel.updateUser(data)
    }

    override fun <T> onUpdate(data: T) {
        UserBottomFragment.newInstance(data as DataUser)
            .show(supportFragmentManager, "Bottom Client")
    }

    override fun <T> onDelete(data: T) {
        MaterialAlertDialogBuilder(baseContext)
            .setTitle("Menghapus User?")
            .setMessage("Jika anda menghapus user maka semua data termasuk rekap akan terhapus juga!")
            .setNegativeButton(resources.getString(R.string.title_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.title_yes)) { _, _ ->
                viewModel.deleteUser(data as DataUser)
                toast("Data Berhasil dihapus")
            }
            .show()
    }
}