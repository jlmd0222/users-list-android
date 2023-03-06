package jlmd.dev.android.pruebaceiba.core

import jlmd.dev.android.pruebaceiba.core.model.Post
import jlmd.dev.android.pruebaceiba.core.model.User
import jlmd.dev.android.pruebaceiba.common.RequestResult
import jlmd.dev.android.pruebaceiba.common.collectSuspending
import jlmd.dev.android.pruebaceiba.data.database.gateway.OfflineStorageGateway
import jlmd.dev.android.pruebaceiba.common.thenSuspending
import jlmd.dev.android.pruebaceiba.data.service.gateway.UsersGateway
import jlmd.dev.android.pruebaceiba.utils.toPost
import jlmd.dev.android.pruebaceiba.utils.toUser
import jlmd.dev.android.pruebaceiba.utils.toUserEntities
import jlmd.dev.android.pruebaceiba.utils.toUsers

class UsersRepository(
    private val offlineStorageGateway: OfflineStorageGateway,
    private val usersGateway: UsersGateway
) {

    suspend fun getUsers(): RequestResult<List<User>> {
        val storedUsers = offlineStorageGateway.getUsers()
        val hasStoredUsers = storedUsers.isNotEmpty()

        return if (hasStoredUsers){
            RequestResult.Success(storedUsers.toUsers())
        } else {
            getUsersFromApi()
                .collectSuspending { users ->
                    offlineStorageGateway.saveUsers(users.toUserEntities())
                }
        }
    }

    private suspend fun getUsersFromApi(): RequestResult<List<User>> {
        return usersGateway.getUsers()
            .thenSuspending { usersResponse ->
                val users = usersResponse.orEmpty()
                    .distinctBy { user -> user.id }
                    .map { it.toUser() }

                RequestResult.Success(users)
            }
    }

    suspend fun getUserById(userId: Int): RequestResult<User> {
        val userById = offlineStorageGateway.getUserById(userId)
        val user = User(userById.id, userById.name, userById.phone, userById.email, userById.website, userById.username)
        return RequestResult.Success(user)
    }

    suspend fun getPostsByUser(userId: Int): RequestResult<List<Post>> {
        return usersGateway.getPostsByUser(userId)
            .thenSuspending { postResponses ->
                val posts = postResponses.orEmpty()
                    .distinctBy { post -> post.id }
                    .map { it.toPost() }

                RequestResult.Success(posts)
            }
    }
}