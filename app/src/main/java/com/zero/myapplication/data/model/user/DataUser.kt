package com.zero.myapplication.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class DataUser(
    @ColumnInfo(name = "name_user")
    val nama_user: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_user")
    var id_user: Int? = null
}
