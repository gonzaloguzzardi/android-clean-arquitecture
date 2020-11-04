package com.learning.data.retrofit.services

import com.learning.data.retrofit.responses.GetPostsResponse
import com.learning.data.retrofit.services.factories.PostsRetrofitServiceFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsRetrofitService {

    companion object {
        fun create(): PostsRetrofitService = PostsRetrofitServiceFactory.get()
    }

    @GET("api/v1/search_by_date")
    suspend fun getPosts(
        @Query("query") query: String = "android"
    ): GetPostsResponse
}