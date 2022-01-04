package com.zero.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zero.myapplication.data.db.dao.ClientDao
import com.zero.myapplication.data.db.dao.ResultDao
import com.zero.myapplication.data.db.dao.TypeDao
import com.zero.myapplication.data.db.dao.UserDao
import com.zero.myapplication.data.model.user.DataClient
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.data.model.user.DataType
import com.zero.myapplication.data.model.user.DataUser

@Database(
    entities = [
        DataUser::class,
        DataClient::class,
        DataType::class,
        DataResult::class
    ],
    version = 1
)
abstract class RoomDB : RoomDatabase() {

    abstract fun user(): UserDao
    abstract fun client(): ClientDao
    abstract fun type(): TypeDao
    abstract fun result(): ResultDao

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