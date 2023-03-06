package jlmd.dev.android.pruebaceiba.data.service.gateway.dto

import com.squareup.moshi.Json

data class PostResponse(
    @Json(name = "userId") val userId: Int,
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String
)