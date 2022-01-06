package com.zero.myapplication.ui.type

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.myapplication.data.model.user.DataType
import com.zero.myapplication.databinding.ActivityTypeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TypeActivity : AppCompatActivity(), BottomSheet {

    private val binding: ActivityTypeBinding by viewBinding()
    private val viewModel: TypeViewModel by viewModel()
    private val adapter: TypeAdapter = TypeAdapter()

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
        viewModel.listenResult().observe(this, {
            adapter.submitData(it)
        })
    }

    fun back(view: View) {
        finish()
    }

    override fun onSubmit(data: DataType) {
        viewModel.addType(data)
    }
}