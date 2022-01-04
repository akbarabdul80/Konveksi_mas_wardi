package com.zero.myapplication.data.db.dao

import androidx.room.*
import com.zero.myapplication.data.model.user.DataClient
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addClinet(data: DataClient)

    @Query("SELECT * FROM client ORDER BY nama_client ASC")
    fun getClient(): Flow<List<DataClient>>

    @Delete
    fun delete(data: DataClient)
}