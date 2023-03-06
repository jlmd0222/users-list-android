package jlmd.dev.android.pruebaceiba.core.usecases

import jlmd.dev.android.pruebaceiba.core.UsersRepository
import jlmd.dev.android.pruebaceiba.core.model.Post
import jlmd.dev.android.pruebaceiba.common.RequestResult

class GetPostsByUserUseCase(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(userId: Int): RequestResult<List<Post>> {
        return usersRepository.getPostsByUser(userId)
    }
}