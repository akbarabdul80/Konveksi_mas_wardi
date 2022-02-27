package com.zero.konveksiku.data.model.user

import androidx.room.Embedded
import androidx.room.Relation

data class DataRekapResultMany(
    @Embedded val result: DataRekap,
    @Relation(
        parentColumn = "id_rekap",
        entityColumn = "rekap_id"
    )
    val client: List<DataRekapResult>
)
