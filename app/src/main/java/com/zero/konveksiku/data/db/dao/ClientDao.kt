package com.zero.konveksiku.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zero.konveksiku.data.model.user.DataClient

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addClinet(data: DataClient)

    @Query("SELECT * FROM client ORDER BY nama_client ASC")
    fun getClient(): LiveData<List<DataClient>>

    @Delete
    fun delete(data: DataClient)

    @Update
    fun update(data: DataClient)
}