package com.learning.domain.usecases

import com.learning.domain.model.PostItemDomainModel
import com.learning.domain.repositories.PostsRepository
import java.io.IOException

class GetPostsUseCase(private val postsRepository: PostsRepository) {
    sealed class Result {
        data class Success(val data: List<PostItemDomainModel>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(): Result {
        return try {
            val posts = postsRepository.getPosts().filter { !it.id.isNullOrBlank() }
            Result.Success(posts)
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}