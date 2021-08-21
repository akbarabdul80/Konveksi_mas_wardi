package com.zero.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zero.myapplication.data.db.RoomDB
import com.zero.myapplication.data.model.user.DataUser
import com.zero.myapplication.root.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var db: RoomDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        db = Room.databaseBuilder(applicationContext, RoomDB::class.java, "book-db").build()

        GlobalScope.launch {
//            App.db.user().addUser(DataUser(1, "Test"))
//            App.db.user().addUser(DataUser(2, "Test"))
        }
    }
}