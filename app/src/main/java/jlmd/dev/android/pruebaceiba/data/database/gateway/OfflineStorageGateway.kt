package jlmd.dev.android.pruebaceiba.data.database.gateway

import jlmd.dev.android.pruebaceiba.data.BaseGateway
import jlmd.dev.android.pruebaceiba.data.database.daos.UserDao
import jlmd.dev.android.pruebaceiba.data.database.entities.UserEntity

class OfflineStorageGateway(
    private val userDao: UserDao
): BaseGateway() {

    suspend fun getUsers() = userDao.getUsersLocal()

    suspend fun saveUsers(users: List<UserEntity>) = userDao.saveUsers(users)

    suspend fun getUserById(userId: Int) = userDao.getUserById(userId)
}