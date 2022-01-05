package com.zero.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.zero.myapplication.data.db.RoomDB
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.data.model.user.DataResultClientUserType
import com.zero.myapplication.data.model.user.DataUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val db: RoomDB
) : ViewModel() {

    private lateinit var result: LiveData<List<DataResultClientUserType>>

    init {
        subscribeResult()
    }

    fun addUser(dataUser: DataUser) {
        viewModelScope.launch(Dispatchers.IO) {
            db.user().addUser(dataUser)
        }
    }

    fun addResult(dataResult: DataResult) {
        viewModelScope.launch(Dispatchers.IO) {
            db.result().addResult(dataResult)
        }
    }

    fun listenResult(): LiveData<List<DataResultClientUserType>> {
        return result
    }


    private fun subscribeResult() {
        result = db.result().getResult()
    }
}
