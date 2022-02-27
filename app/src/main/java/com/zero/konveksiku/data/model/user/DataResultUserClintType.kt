package com.zero.konveksiku.data.model.user

import androidx.room.Embedded
import androidx.room.Relation

data class DataResultUserClintType(
    @Embedded val result: DataResult,
    @Relation(
        parentColumn = "client_id",
        entityColumn = "id_client"
    )
    val client: DataClient,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id_user"
    )
    val user: DataUser,
    @Relation(
        parentColumn = "type_id",
        entityColumn = "id_type"
    )
    val type: DataType,
)