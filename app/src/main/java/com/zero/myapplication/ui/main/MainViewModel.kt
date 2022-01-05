package com.zero.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.zero.myapplication.data.db.RoomDB
import com.zero.myapplication.data.model.user.DataResult
import com.zero.myapplication.data.model.user.DataResultClientUserType

class MainViewModel(
    private val db: RoomDB
) : ViewModel() {

    private lateinit var result: LiveData<List<DataResultClientUserType>>

    init {
        subscribeResult()
    }

    fun addResult(dataResult: DataResult){
        db.result().addResult(dataResult)
    }

    fun listenResult(): LiveData<List<DataResultClientUserType>> {
        return result
    }


    private fun subscribeResult() {
        result = db.result().getResult().map { data ->
            data.reversed().map { DataResultClientUserType(it.result, it.client, it.user, it.type) }
        }
    }
}
