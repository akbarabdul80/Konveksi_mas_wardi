package com.zero.myapplication.data.db.dao

import androidx.room.*
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.data.model.user.DataResultClientUserType

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addResult(data: DataResult)

    @Transaction
    @Query("SELECT * FROM result")
    fun getResult(): List<DataResultClientUserType>

}