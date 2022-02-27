package com.zero.konveksiku.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zero.konveksiku.data.db.dao.*
import com.zero.konveksiku.data.model.user.*

@Database(
    entities = [
        DataUser::class,
        DataClient::class,
        DataType::class,
        DataResult::class,
        DataRekap::class,
        DataRekapResult::class
    ],
    version = 1,
    exportSchema = true
)
abstract class RoomDB : RoomDatabase() {

    abstract fun user(): UserDao
    abstract fun client(): ClientDao
    abstract fun type(): TypeDao
    abstract fun result(): ResultDao
    abstract fun rekap(): RekapDao

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