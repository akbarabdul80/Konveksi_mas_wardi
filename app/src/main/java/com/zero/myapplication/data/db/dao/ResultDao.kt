package com.zero.myapplication.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zero.myapplication.data.model.user.DataQtyResult
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.data.model.user.DataResultClient
import com.zero.myapplication.data.model.user.DataResultClientUserType

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addResult(data: DataResult)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addRekap(data: List<DataResult>)

    @Transaction
    @Query("SELECT * FROM result WHERE status = 0 ORDER BY user_id, client_id ASC")
    fun getResultRekapUser(): LiveData<List<DataResultClientUserType>>

//    @Transaction
//    @Query("SELECT * FROM result WHERE id_result = :id_result ORDER BY user_id, client_id ASC")
//    fun getResultRekapUserByID(id_result: Int): LiveData<List<DataResultClientUserType>>

    @Transaction
    @Query("SELECT * FROM result WHERE id_result = :id_result ORDER BY user_id, client_id ASC")
    fun getResultRekapUserByID(id_result: Int): LiveData<List<DataResultClientUserType>>

    @Transaction
    @Query("SELECT nama_client, client_id, SUM(qty) as all_qty, nama_type  FROM result JOIN client ON client_id = id_client JOIN type ON type_id = id_type WHERE status = 0 GROUP BY client_id, type_id")
    fun getResultRekapClient(): LiveData<List<DataResultClient>>

    @Transaction
    @Query("SELECT nama_client, client_id, SUM(qty) as all_qty, nama_type  FROM result JOIN client ON client_id = id_client JOIN type ON type_id = id_type WHERE id_result = :id_result GROUP BY client_id, type_id")
    fun getResultRekapClientByID(id_result: Int): LiveData<List<DataResultClient>>

    @Query("SELECT * FROM result WHERE status = 0")
    fun getAllNotRekap(): LiveData<List<DataResult>>

    @Query("SELECT SUM(qty) as all_qty FROM result WHERE status = 0")
    fun getAllQty(): LiveData<DataQtyResult>

    @Transaction
    @Query("SELECT * FROM result WHERE date LIKE '%' ||:date || '%'  ORDER BY user_id, client_id ASC")
    fun getResultToday(date: String): LiveData<List<DataResultClientUserType>>

    @Query("UPDATE result SET status = 1 WHERE status = 0")
    fun updateRekap()
}