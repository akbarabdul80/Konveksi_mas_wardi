package com.zero.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zero.myapplication.data.db.RoomDB
import com.zero.myapplication.data.model.user.DataClient
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.data.model.user.DataType
import com.zero.myapplication.data.model.user.DataUser
import com.zero.myapplication.root.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var db: RoomDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
//            App.db.type().addType(DataType("Type 1"))
//            App.db.type().addType(DataType("Type 2"))
//            App.db.client().addClinet(DataClient("Batik Saya"))

//            App.db.result().addResult(DataResult(1, 1,1, "01-12-2002", 10))

            Log.e("DATA", App.db.result().getResult().toString())
        }
    }
}