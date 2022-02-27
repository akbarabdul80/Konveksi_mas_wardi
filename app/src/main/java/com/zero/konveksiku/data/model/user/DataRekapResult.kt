package com.zero.konveksiku.data.model.user

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "rekap_result",
    foreignKeys = [
        ForeignKey(
            entity = DataRekap::class,
            parentColumns = arrayOf("id_rekap"),
            childColumns = arrayOf("rekap_id"),
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = DataResult::class,
            parentColumns = arrayOf("id_result"),
            childColumns = arrayOf("result_id"),
            onDelete = CASCADE
        )
    ],
    indices = [
        Index("rekap_id"),
        Index("result_id"),
    ]
)
data class DataRekapResult(
    @ColumnInfo(name = "rekap_id")
    var rekap_id: Int,
    @ColumnInfo(name = "result_id")
    val result_id: Int,
    @PrimaryKey
    @ColumnInfo(name = "id_rekap_result")
    val id_rekap_result: Int? = null,
)
