package com.zero.myapplication.data.model.user

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "rekap"
)
data class DataRekap(
    @ColumnInfo(name = "start_date")
    val start_date: String,
    @ColumnInfo(name = "end_date")
    val end_date: String,
    @ColumnInfo(name = "qty")
    var qty: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_rekap")
    var id_rekap: Int? = null
}
