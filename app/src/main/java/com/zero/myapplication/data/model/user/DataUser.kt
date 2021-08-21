package com.zero.myapplication.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class DataUser(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_user") val id_user: Int,
    val name: String
)
