package jlmd.dev.android.pruebaceiba.data.service.gateway.dto

import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "website") val website: String
)
