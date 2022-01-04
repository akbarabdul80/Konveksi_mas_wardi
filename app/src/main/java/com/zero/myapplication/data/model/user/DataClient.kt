package com.zero.myapplication.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class DataClient(
    @ColumnInfo(name = "nama_client")
    val nama_client: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_client")
    var id_client: Int? = null
}
