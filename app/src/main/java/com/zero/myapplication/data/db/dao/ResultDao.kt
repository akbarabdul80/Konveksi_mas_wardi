package com.zero.myapplication.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.data.model.user.DataResultClientUserType

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addResult(data: DataResult)

    @Transaction
    @Query("SELECT * FROM result ORDER BY user_id ASC")
    fun getResult(): LiveData<List<DataResultClientUserType>>

}