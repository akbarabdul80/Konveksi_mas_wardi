package com.zero.konveksiku.data.model.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "client"
)
data class DataClient(
    @ColumnInfo(name = "nama_client")
    val nama_client: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_client")
    var id_client: Int? = null
) : Parcelable
