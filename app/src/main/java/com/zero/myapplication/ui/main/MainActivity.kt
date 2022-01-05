package com.zero.myapplication.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.myapplication.R
import com.zero.myapplication.data.db.RoomDB
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var db: RoomDB

    private val viewModel: MainViewModel by viewModel()
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel.addResult(DataResult(1, 1, 1, "01-12-2002", 10))

//        binding.tvHome.onClick {
//            viewModel.addResult(DataResult(1, 1, 1, "01-12-2022", 10))
//        }

//        GlobalScope.launch {
////            App.db.type().addType(DataType("Type 1"))
////            App.db.type().addType(DataType("Type 2"))
////            App.db.client().addClinet(DataClient("Batik Saya"))
//
//
//            App.db.result().addResult(DataResult(1, 1, 1, "01-12-2002", 10))
//
//            Log.e("DATA", App.db.result().getResult().toString())
//        }

        viewModel.listenResult().observe(this, {
            Log.e("data", it.toString())
        })
    }
}