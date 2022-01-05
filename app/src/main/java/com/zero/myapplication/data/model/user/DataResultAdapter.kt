package com.zero.myapplication.data.model.user

import androidx.room.Embedded
import androidx.room.Relation

data class DataResultAdapter(
    val result: DataResult,
    val client: DataClient,
    val user: DataUser,
    val type: DataType,
    val itemType: Int
)