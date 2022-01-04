package com.zero.myapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zero.myapplication.data.model.user.DataUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(data: DataUser)

    @Query("SELECT * FROM user ORDER BY name_user ASC")
    fun getUser(): Flow<List<DataUser>>
}