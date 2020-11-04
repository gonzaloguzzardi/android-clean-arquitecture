package com.learning.data.repositories

import com.learning.data.model.toDomainModel
import com.learning.data.model.toEntity
import com.learning.data.retrofit.services.PostsRetrofitService
import com.learning.data.room.deletedposts.DeletedPostEntity
import com.learning.data.room.deletedposts.DeletedPostsDao
import com.learning.data.room.posts.PostsDao
import com.learning.domain.model.PostItemDomainModel
import com.learning.domain.repositories.PostsRepository

class PostsRepositoryImpl(
    private val postsRetrofitService: PostsRetrofitService,
    private val postsDao: PostsDao,
    private val deletedPostsDao: DeletedPostsDao
) : PostsRepository {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun getPosts(isNetworkConnected: Boolean): List<PostItemDomainModel> {
        return try {
            val deletedPosts = getDeletedPosts()
            val posts = if (isNetworkConnected) {
                val remotePosts =
                    postsRetrofitService.getPosts().posts.filter { !deletedPosts.contains(it.id) }
                postsDao.insertPosts(remotePosts.map { it.toEntity() })
                remotePosts.map { it.toDomainModel() }
            } else {
                getPostsFromDatabase(deletedPosts)
            }
            posts
        } catch (e: Exception) {
            val deletedPosts = getDeletedPosts()
            getPostsFromDatabase(deletedPosts)
        }
    }

    override suspend fun deletePost(postId: String) {
        deletedPostsDao.insertDeletedPost(DeletedPostEntity(postId))
    }

    private suspend fun getPostsFromDatabase(deletedPosts: Set<String>): List<PostItemDomainModel> {
        return postsDao.getAll().filter { !deletedPosts.contains(it.id) }.map { it.toDomainModel() }
    }

    private suspend fun getDeletedPosts(): Set<String> =
        deletedPostsDao.getAll().map { it.id }.toHashSet()

}