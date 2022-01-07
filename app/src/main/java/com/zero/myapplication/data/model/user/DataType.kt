package com.zero.myapplication.data.model.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "type")
data class DataType(
    @ColumnInfo(name = "nama_type")
    val nama_type: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_type")
    var id_type: Int? = null
) : Parcelable
