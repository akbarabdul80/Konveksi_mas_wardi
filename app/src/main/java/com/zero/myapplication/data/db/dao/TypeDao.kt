package com.zero.myapplication.data.db.dao

import androidx.room.*
import com.zero.myapplication.data.model.user.DataClient
import com.zero.myapplication.data.model.user.DataType
import kotlinx.coroutines.flow.Flow

@Dao
interface TypeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addType(data: DataType)

    @Query("SELECT * FROM type")
    fun getType(): Flow<List<DataType>>

    @Query("SELECT * FROM type WHERE id_type = :id")
    fun getTypeID(id: Int): DataType

    @Delete
    fun delete(data: DataClient)

}