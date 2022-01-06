package com.zero.myapplication.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.zero.myapplication.data.db.dao.ClientDao
import com.zero.myapplication.data.model.user.DataClient
import kotlinx.coroutines.flow.Flow

class ClientRepository(private val db: ClientDao) {

    fun getAllUser(): LiveData<List<DataClient>> = db.getClient()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(client: DataClient) {
        db.addClinet(client)
    }

}