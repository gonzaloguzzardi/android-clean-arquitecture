package com.learning.domain.repositories

import com.learning.domain.model.PostItemDomainModel

interface PostsRepository {
    suspend fun getPosts(isNetworkConnected: Boolean): List<PostItemDomainModel>
    suspend fun deletePost(postId: String)
}