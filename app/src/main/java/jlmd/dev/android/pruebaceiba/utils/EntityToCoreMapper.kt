package jlmd.dev.android.pruebaceiba.utils

import jlmd.dev.android.pruebaceiba.data.database.entities.UserEntity
import jlmd.dev.android.pruebaceiba.core.model.User
import jlmd.dev.android.pruebaceiba.views.users.model.UserItem

fun List<UserEntity>.toUsers() = map { it.toUser() }

fun UserEntity.toUser() =
    User(
        id = id,
        name = name,
        phone = phone,
        email = email,
        website = website,
        username = username
    )

fun List<User>.toUserEntities() = map { it.toUserEntity() }

fun User.toUserEntity() =
    UserEntity(
        id = id,
        name = name,
        email = email,
        phone = phone,
        website = website,
        username = username
    )

fun User.toUserItem(): UserItem =
    UserItem(
        id = id,
        name = name,
        phone = phone,
        email = email
    )