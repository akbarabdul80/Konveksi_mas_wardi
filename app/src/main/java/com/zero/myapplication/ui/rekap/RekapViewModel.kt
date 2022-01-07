package com.zero.myapplication.ui.rekap

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zero.myapplication.data.db.RoomDB
import com.zero.myapplication.data.model.user.DataResultClient
import com.zero.myapplication.data.model.user.DataResultClientUserType

class RekapViewModel(
    private val db: RoomDB
) : ViewModel() {
    private lateinit var resultUser: LiveData<List<DataResultClientUserType>>
    private lateinit var resultClient: LiveData<List<DataResultClient>>

    init {
        subscribeResultUser()
        subscribeResultClinet()
    }


    fun listenResult(): LiveData<List<DataResultClientUserType>> {
        return resultUser
    }

    fun listenResultClinet(): LiveData<List<DataResultClient>> {
        return resultClient
    }

    private fun subscribeResultUser() {
        resultUser = db.result().getResultRekapUser()
    }

    private fun subscribeResultClinet() {
        resultClient = db.result().getResultRekapClient()
    }

}