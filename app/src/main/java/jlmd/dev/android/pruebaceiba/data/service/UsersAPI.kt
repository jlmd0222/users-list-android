package jlmd.dev.android.pruebaceiba.data.service

import jlmd.dev.android.pruebaceiba.data.service.gateway.dto.PostResponse
import jlmd.dev.android.pruebaceiba.data.service.gateway.dto.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersAPI {

    @GET("/users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET("/posts")
    suspend fun getPostsByUser(@Query("userId") userId: Int): Response<List<PostResponse>>
}
