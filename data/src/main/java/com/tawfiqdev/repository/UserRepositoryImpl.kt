package com.tawfiqdev.repository

import com.tawfiqdev.database.dao.UserDao
import com.tawfiqdev.mapper.toDomain
import com.tawfiqdev.mapper.toEntity
import com.tawfiqdev.model.User
import com.tawfiqdev.utils.ErrorState
import com.tawfiqdev.utils.ResultOutput
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun insert(user: User): ResultOutput<Unit, ErrorState> =
        try {
            userDao.insert(user.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(ErrorState.Database(e))
        }

    override suspend fun update(user: User): ResultOutput<Unit, ErrorState> =
        try {
            userDao.update(user.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(ErrorState.Database(e))
        }

    override suspend fun delete(user: User): ResultOutput<Unit, ErrorState> =
        try {
            userDao.delete(user.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(ErrorState.Database(e))
        }

    override suspend fun getUserById(id: Long): ResultOutput<User?, ErrorState> =
        try {
            val entity = userDao.getById(id)
            ResultOutput.Success(entity?.toDomain())
        } catch (e: Exception) {
            ResultOutput.Failure(ErrorState.Database(e))
        }
}