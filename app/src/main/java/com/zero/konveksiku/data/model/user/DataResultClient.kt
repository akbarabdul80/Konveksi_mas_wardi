package com.zero.konveksiku.data.model.user

data class DataResultClient(
    val nama_client: String,
    val client_id: String,
    val all_qty: Int,
    val nama_type: String,
    var itemType: Int? = 1
)