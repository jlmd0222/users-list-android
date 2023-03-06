package jlmd.dev.android.pruebaceiba.utils

import jlmd.dev.android.pruebaceiba.core.model.Post
import jlmd.dev.android.pruebaceiba.core.model.User
import jlmd.dev.android.pruebaceiba.data.service.gateway.dto.PostResponse
import jlmd.dev.android.pruebaceiba.data.service.gateway.dto.UserResponse

fun UserResponse.toUser() =
    User(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )

fun PostResponse.toPost() =
    Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )