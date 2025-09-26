package com.tawfiqdev.mapper

import com.tawfiqdev.database.entity.UserEntity
import com.tawfiqdev.model.User

fun UserEntity.toDomain(): User = User(
    id = id,
    name = name,
    email = email,
    phone = phone
)

fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    name = name,
    email = email,
    phone = phone
)