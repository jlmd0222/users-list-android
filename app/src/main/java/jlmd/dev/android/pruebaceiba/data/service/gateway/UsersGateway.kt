package jlmd.dev.android.pruebaceiba.data.service.gateway

import jlmd.dev.android.pruebaceiba.data.BaseGateway
import jlmd.dev.android.pruebaceiba.data.service.UsersAPI

class UsersGateway(
    private val usersAPI: UsersAPI
): BaseGateway() {

    suspend fun getUsers() = getResult {
        executeRequest {
            usersAPI.getUsers()
        }
    }

    suspend fun getPostsByUser(userId: Int) = getResult {
        executeRequest {
            usersAPI.getPostsByUser(userId)
        }
    }
}
