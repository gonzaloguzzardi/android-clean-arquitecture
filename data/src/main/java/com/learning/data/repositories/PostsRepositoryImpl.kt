package com.learning.data.repositories

import com.learning.data.model.toDomainModel
import com.learning.data.model.toEntity
import com.learning.data.retrofit.services.PostsRetrofitService
import com.learning.data.room.PostsDao
import com.learning.domain.model.PostItemDomainModel
import com.learning.domain.repositories.PostsRepository

class PostsRepositoryImpl(
    private val postsRetrofitService: PostsRetrofitService,
    private val postsDao: PostsDao
) : PostsRepository {

    override suspend fun getPosts(isNetworkConnected: Boolean): List<PostItemDomainModel> {
        return try {
            val posts = if (isNetworkConnected) {
                val remotePosts = postsRetrofitService.getPosts().posts
                postsDao.insertPosts(remotePosts.map { it.toEntity() })
                remotePosts.map { it.toDomainModel() }
            } else {
                postsDao.getAll().map { it.toDomainModel() }
            }
            posts
        } catch (e: Exception) {
            postsDao.getAll().map { it.toDomainModel() }
        }
    }
}