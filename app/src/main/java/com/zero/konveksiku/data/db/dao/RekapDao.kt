package com.zero.konveksiku.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zero.konveksiku.data.model.user.DataRekap
import com.zero.konveksiku.data.model.user.DataRekapResult
import com.zero.konveksiku.data.model.user.DataRekapResultMany

@Dao
interface RekapDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addRekap(data: DataRekap): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addRekapResult(data: List<DataRekapResult>)

    @Transaction
    @Query("SELECT * FROM rekap ORDER BY start_date ASC")
    fun getRekap(): LiveData<List<DataRekap>>

    @Transaction
    @Query("SELECT * FROM rekap ORDER BY start_date ASC")
    fun getRekapResult(): LiveData<List<DataRekapResultMany>>

    @Transaction
    @Query("SELECT * FROM rekap WHERE id_rekap = :id_rekap ORDER BY start_date ASC")
    fun getRekapResultByID(id_rekap: Int): LiveData<DataRekapResultMany>

}