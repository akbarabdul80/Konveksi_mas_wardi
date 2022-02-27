package com.zero.konveksiku.ui.rekap

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zero.konveksiku.data.db.RoomDB
import com.zero.konveksiku.data.model.user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RekapViewModel(
    private val db: RoomDB
) : ViewModel() {
    private lateinit var resultUser: LiveData<List<DataResultClientUserType>>
    private lateinit var resultClient: LiveData<List<DataResultClient>>
    private lateinit var resultQty: LiveData<DataQtyResult>
    private lateinit var result: LiveData<List<DataResult>>
    private lateinit var resultRekap: LiveData<List<DataRekap>>

    init {
        subscribeResultUser()
        subscribeResultClinet()
        subscribeResultQty()
        subscribeResult()
        subscribeRekap()
    }


    fun listenResult(): LiveData<List<DataResultClientUserType>> {
        return resultUser
    }

    fun listenResultClinet(): LiveData<List<DataResultClient>> {
        return resultClient
    }

    fun listenResultQty(): LiveData<DataQtyResult> {
        return resultQty
    }

    fun listenResultNow(): LiveData<List<DataResult>> {
        return result
    }

    fun listenRekap(): LiveData<List<DataRekap>> {
        return resultRekap
    }

    fun listenResultClinetByID(id_rekap: Int): LiveData<List<DataResultClient>> {
        return db.result().getResultRekapClientByID(id_rekap)
    }

    fun listenResultByID(id_rekap: Int): LiveData<List<DataResultClientUserType>> {
        return db.result().getResultRekapUserByID(id_rekap)
    }

    fun addRekap(dataRekap: DataRekap, dataRekapResult: List<DataRekapResult>) {
        viewModelScope.launch(Dispatchers.IO) {
            val idRekap: Long = db.rekap().addRekap(dataRekap)
            dataRekapResult.forEach {
                it.apply {
                    rekap_id = idRekap.toInt()
                }
            }
            db.rekap().addRekapResult(dataRekapResult)
            db.result().updateRekap()
        }
    }

    private fun subscribeResultUser() {
        resultUser = db.result().getResultRekapUser()
    }

    private fun subscribeResultClinet() {
        resultClient = db.result().getResultRekapClient()
    }

    private fun subscribeResultQty() {
        resultQty = db.result().getAllQty()
    }

    private fun subscribeResult() {
        result = db.result().getAllNotRekap()
    }

    private fun subscribeRekap() {
        resultRekap = db.rekap().getRekap()
    }
}