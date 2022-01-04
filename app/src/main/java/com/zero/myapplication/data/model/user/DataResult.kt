package com.zero.myapplication.data.model.user

import androidx.room.*

@Entity(
    tableName = "result"
)
data class DataResult(
    @ColumnInfo(name = "client_id")
    val client_id: Int,
    @ColumnInfo(name = "user_id")
    val user_id: Int,
    @ColumnInfo(name = "type_id")
    val type_id: Int,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "qty")
    val qty: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_result")
    var id_result: Int? = null
}
