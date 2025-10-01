package com.tawfiqdev.repository

import com.tawfiqdev.model.User
import com.tawfiqdev.utils.ErrorState
import com.tawfiqdev.utils.ResultOutput
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insert(user: User): ResultOutput<Unit, ErrorState>
    suspend fun update(user: User): ResultOutput<Unit, ErrorState>
    suspend fun delete(user: User): ResultOutput<Unit, ErrorState>
    suspend fun getUserById(id: Long): ResultOutput<User?, ErrorState>
}
