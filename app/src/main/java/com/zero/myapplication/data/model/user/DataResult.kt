package com.zero.myapplication.data.model.user

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

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
    ],
    indices = [
        Index("client_id"),
        Index("user_id"),
        Index("type_id"),
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
    var qty: Int,
    @ColumnInfo(name = "status")
    var status: Boolean? = false
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_result")
    var id_result: Int? = null
}
