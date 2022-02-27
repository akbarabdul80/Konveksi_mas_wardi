package com.zero.konveksiku.data.model.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class DataUser(
    @ColumnInfo(name = "name_user")
    val nama_user: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_user")
    var id_user: Int? = null
) : Parcelable
