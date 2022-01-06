package com.zero.myapplication.ui.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zero.myapplication.data.db.RoomDB
import com.zero.myapplication.data.model.user.DataClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientViewModel(
    private val db: RoomDB
) : ViewModel() {
    private lateinit var result: LiveData<List<DataClient>>

    init {
        subscribeClient()
    }

    fun addClient(datacClient: DataClient) {
        viewModelScope.launch(Dispatchers.IO) {
            db.client().addClinet(datacClient)
        }
    }

    fun deleteClient(datacClient: DataClient) {
        viewModelScope.launch(Dispatchers.IO) {
            db.client().delete(datacClient)
        }
    }

    fun updateClient(datacClient: DataClient) {
        viewModelScope.launch(Dispatchers.IO) {
            db.client().update(datacClient)
        }
    }

    fun listenResult(): LiveData<List<DataClient>> {
        return result
    }


    private fun subscribeClient() {
        result = db.client().getClient()
    }
}