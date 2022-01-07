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
    @Query("SELECT * FROM result ORDER BY user_id, client_id ASC")
    fun getResult(): LiveData<List<DataResultClientUserType>>

    @Transaction
    @Query("SELECT * FROM result WHERE date LIKE '%' ||:date || '%'  ORDER BY user_id, client_id ASC")
    fun getResultToday(date: String): LiveData<List<DataResultClientUserType>>

}