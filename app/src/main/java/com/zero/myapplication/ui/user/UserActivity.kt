package com.zero.myapplication.ui.user

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.myapplication.data.model.user.DataUser
import com.zero.myapplication.databinding.ActivityUserBinding
import com.zero.myapplication.ui.bottom_action.BottomActionFragment
import org.koin.android.viewmodel.ext.android.viewModel

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
        viewModel.listenResult().observe(this, {
            adapter.submitData(it)
        })
    }

    fun back(view: View) {
        finish()
    }

    override fun onSubmit(data: DataUser) {
        viewModel.addUser(data)
    }

    override fun onUpdate(data: DataUser) {
        viewModel.updateClient(data)
    }

    override fun onDelete(data: DataUser) {

    }

    override fun <T> onUpdate(data: T) {
        UserBottomFragment.newInstance(data as DataUser)
            .show(supportFragmentManager, "Bottom Client")
    }

    override fun <T> onDelete(data: T) {

    }
}