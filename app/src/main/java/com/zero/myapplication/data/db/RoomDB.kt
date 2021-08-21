package com.zero.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zero.myapplication.data.db.dao.UserDao
import com.zero.myapplication.data.model.user.DataUser

@Database(
    entities = [
        DataUser::class
    ],
    version = 1
)
abstract class RoomDB : RoomDatabase() {

    abstract fun user(): UserDao

    companion object {
        @Volatile
        private var instance: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            return instance ?: synchronized(this) {
                val instances = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "konveksi.db"
                ).build()
                instance = instances
                instances
            }
        }
    }
}