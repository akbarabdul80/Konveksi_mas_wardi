package com.zero.myapplication.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "result",
    foreignKeys = [
        ForeignKey(
            entity = DataClient::class,
            parentColumns = arrayOf("id_client"),
            childColumns = arrayOf("client_id"),
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = DataUser::class,
            parentColumns = arrayOf("id_user"),
            childColumns = arrayOf("user_id"),
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = DataType::class,
            parentColumns = arrayOf("id_type"),
            childColumns = arrayOf("type_id"),
            onDelete = CASCADE
        ),
    ]
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
    var qty: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_result")
    var id_result: Int? = null
}
