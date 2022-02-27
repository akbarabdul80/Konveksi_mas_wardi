package com.zero.konveksiku.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zero.konveksiku.data.db.RoomDB
import com.zero.konveksiku.data.model.user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    private val db: RoomDB
) : ViewModel() {

    private lateinit var result: LiveData<List<DataResultClientUserType>>
    private lateinit var resultUser: LiveData<List<DataUser>>
    private lateinit var resultClient: LiveData<List<DataClient>>
    private lateinit var resultType: LiveData<List<DataType>>

    init {
        subscribeResult()
        subscribeResultUser()
        subscribeResultClient()
        subscribeResultType()
    }

    fun addResult(dataResult: DataResult) {
        viewModelScope.launch(Dispatchers.IO) {
            db.result().addResult(dataResult)
        }
    }

    fun listenResult(): LiveData<List<DataResultClientUserType>> {
        return result
    }


    fun listenResultUser(): LiveData<List<DataUser>> {
        return resultUser
    }


    fun listenResultClient(): LiveData<List<DataClient>> {
        return resultClient
    }


    fun listenResultType(): LiveData<List<DataType>> {
        return resultType
    }


    @SuppressLint("SimpleDateFormat")
    private fun subscribeResult() {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())

        result = db.result().getResultToday(currentDate)
    }

    private fun subscribeResultUser() {
        resultUser = db.user().getUser()
    }

    private fun subscribeResultClient() {
        resultClient = db.client().getClient()
    }

    private fun subscribeResultType() {
        resultType = db.type().getType()
    }
}
