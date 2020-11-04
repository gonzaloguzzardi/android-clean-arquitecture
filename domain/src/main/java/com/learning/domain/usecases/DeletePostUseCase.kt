package com.learning.domain.usecases

import com.learning.domain.repositories.PostsRepository

class DeletePostUseCase(private val postsRepository: PostsRepository) {

    suspend fun execute(postId: String) = postsRepository.deletePost(postId)
}