package com.zero.konveksiku.ui.type

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zero.konveksiku.data.db.RoomDB
import com.zero.konveksiku.data.model.user.DataType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TypeViewModel(
    private val db: RoomDB
) : ViewModel() {
    private lateinit var result: LiveData<List<DataType>>

    init {
        subscribeType()
    }

    fun addType(dataType: DataType) {
        viewModelScope.launch(Dispatchers.IO) {
            db.type().addType(dataType)
        }
    }

    fun deleteType(dataType: DataType) {
        viewModelScope.launch(Dispatchers.IO) {
            db.type().delete(dataType)
        }
    }

    fun updateType(dataType: DataType) {
        viewModelScope.launch(Dispatchers.IO) {
            db.type().update(dataType)
        }
    }

    fun listenResult(): LiveData<List<DataType>> {
        return result
    }


    private fun subscribeType() {
        result = db.type().getType()
    }
}