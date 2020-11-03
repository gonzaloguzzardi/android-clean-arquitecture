package com.learning.data.repositories

import com.learning.data.model.toDomainModel
import com.learning.data.retrofit.services.PostsRetrofitService
import com.learning.domain.model.PostItemDomainModel
import com.learning.domain.repositories.PostsRepository

class PostsRepositoryImpl(private val postsRetrofitService: PostsRetrofitService) :
    PostsRepository {

    override suspend fun getPosts(): List<PostItemDomainModel> =
        postsRetrofitService.getPosts().posts.map {
            it.toDomainModel()
        }
}