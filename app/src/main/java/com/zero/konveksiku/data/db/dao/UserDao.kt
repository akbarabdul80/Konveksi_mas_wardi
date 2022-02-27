package com.zero.konveksiku.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zero.konveksiku.data.model.user.DataUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(data: DataUser)

    @Query("SELECT * FROM user ORDER BY name_user ASC")
    fun getUser(): LiveData<List<DataUser>>

    @Delete
    fun delete(data: DataUser)

    @Update
    fun update(dataUser: DataUser)
}