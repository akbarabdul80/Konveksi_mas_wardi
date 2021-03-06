package com.zero.konveksiku.data.model.user

data class DataResultAdapter(
    val result: DataResult,
    val client: DataClient,
    val user: DataUser,
    val type: DataType,
    val itemType: Int
)