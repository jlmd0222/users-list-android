package jlmd.dev.android.pruebaceiba.core.usecases

import jlmd.dev.android.pruebaceiba.core.UsersRepository
import jlmd.dev.android.pruebaceiba.core.model.User
import jlmd.dev.android.pruebaceiba.common.RequestResult

class GetUserByIdUseCase(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(userId: Int): RequestResult<User> {
        return usersRepository.getUserById(userId)
    }
}