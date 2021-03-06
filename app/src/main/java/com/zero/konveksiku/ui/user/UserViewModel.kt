package com.zero.konveksiku.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zero.konveksiku.data.db.RoomDB
import com.zero.konveksiku.data.model.user.DataUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
    private val db: RoomDB
) : ViewModel() {
    private lateinit var result: LiveData<List<DataUser>>

    init {
        subscribeClient()
    }

    fun addUser(dataUser: DataUser) {
        viewModelScope.launch(Dispatchers.IO) {
            db.user().addUser(dataUser)
        }
    }

    fun deleteUser(dataUser: DataUser) {
        viewModelScope.launch(Dispatchers.IO) {
            db.user().delete(dataUser)
        }
    }

    fun updateUser(dataUser: DataUser) {
        viewModelScope.launch(Dispatchers.IO) {
            db.user().update(dataUser)
        }
    }

    fun listenResult(): LiveData<List<DataUser>> {
        return result
    }


    private fun subscribeClient() {
        result = db.user().getUser()
    }
}