package com.tawfiqdev.usecase

import com.tawfiqdev.model.User
import com.tawfiqdev.repository.UserRepository
import com.tawfiqdev.utils.ErrorState
import com.tawfiqdev.utils.ResultOutput

class GetUserByIdUseCase (private val repository: UserRepository){
    suspend operator fun invoke(id: Long): ResultOutput<User?, ErrorState> = repository.getUserById(id)
}

class InsertUserUseCase (private val repository: UserRepository) {
    suspend operator fun invoke(input: User): ResultOutput<Unit, ErrorState>  {
        if (input.email.isBlank()){
            return ResultOutput.Failure(ErrorState.Validation("Email is empty !"))
        }
        if (input.name.isBlank()){
            return ResultOutput.Failure(ErrorState.Validation("Name is empty !"))
        }
        return repository.insert(input.copy(id = 0))
    }
}

class UpdateUserUseCase (private val repository: UserRepository) {
    suspend operator fun invoke(user: User): ResultOutput<Unit, ErrorState> {
        if (user.id <= 0) {
            return ResultOutput.Failure(ErrorState.Validation("Id invalide"))
        }
        return repository.update(user)
    }
}

class DeleteUserUseCase (private val repository: UserRepository) {
    suspend operator fun invoke(user: User): ResultOutput<Unit, ErrorState> {
        if (user.id <= 0) {
            return ResultOutput.Failure(ErrorState.Validation("Id invalide"))
        }
        return repository.delete(user)
    }
}

