package com.zero.myapplication.ui.client

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.myapplication.data.model.user.DataClient
import com.zero.myapplication.databinding.ActivityClientBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ClientActivity : AppCompatActivity(), BottomSheet {

    private val binding: ActivityClientBinding by viewBinding()
    private val viewModel: ClientViewModel by viewModel()
    private val adapter: ClientAdapter = ClientAdapter()

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
        viewModel.listenResult().observe(this, {
            adapter.submitData(it)
        })
    }

    fun back(view: View) {
        finish()
    }

    override fun onSubmit(data: DataClient) {
        viewModel.addClient(data)
    }
}