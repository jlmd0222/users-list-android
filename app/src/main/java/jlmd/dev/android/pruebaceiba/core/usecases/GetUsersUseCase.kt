package jlmd.dev.android.pruebaceiba.core.usecases

import jlmd.dev.android.pruebaceiba.core.UsersRepository
import jlmd.dev.android.pruebaceiba.core.model.User
import jlmd.dev.android.pruebaceiba.common.RequestResult

class GetUsersUseCase(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(): RequestResult<List<User>> {
        return usersRepository.getUsers()
    }
}