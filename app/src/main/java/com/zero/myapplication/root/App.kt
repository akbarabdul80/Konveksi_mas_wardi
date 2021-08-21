package com.zero.myapplication.root

import android.app.Application
import com.zero.myapplication.data.UserRepository
import com.zero.myapplication.data.db.RoomDB

class App : Application() {

    companion object {
        lateinit var db: RoomDB
        val repoUser by lazy {
            UserRepository(db.user())
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = RoomDB.getDatabase(this)
    }

}