package com.zero.myapplication.data

import androidx.annotation.WorkerThread
import com.zero.myapplication.data.db.dao.UserDao
import com.zero.myapplication.data.model.user.DataUser
import kotlinx.coroutines.flow.Flow

class UserRepository(private val db: UserDao) {

    fun getAllUser(): Flow<List<DataUser>> = db.getUser()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: DataUser) {
        db.addUser(user)
    }

}