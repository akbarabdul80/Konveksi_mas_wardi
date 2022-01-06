package com.zero.myapplication.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zero.myapplication.data.model.user.DataType

@Dao
interface TypeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addType(data: DataType)

    @Query("SELECT * FROM type")
    fun getType(): LiveData<List<DataType>>

    @Query("SELECT * FROM type WHERE id_type = :id")
    fun getTypeID(id: Int): DataType

    @Delete
    fun delete(data: DataType)

    @Update
    fun update(data: DataType)

}