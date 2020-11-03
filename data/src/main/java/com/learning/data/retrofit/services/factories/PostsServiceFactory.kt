package com.learning.data.retrofit.services.factories

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.learning.data.BuildConfig
import com.learning.data.retrofit.services.PostsRetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PostsServiceFactory {

    private const val BASE_URL = "http://hn.algolia.com"

    private var postsService: PostsRetrofitService? = null

    @Suppress("UnsafeCallOnNullableType")
    fun get(): PostsRetrofitService {
        if (postsService != null) return postsService!!

        val httpClient = getHttpClient()
        val retrofit = getRetrofitInstance(httpClient)
        postsService = retrofit.create(PostsRetrofitService::class.java)
        return postsService!!
    }

    private fun getRetrofitInstance(client: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
        return builder.build()
    }

    private fun getHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val loggingLevel = if (BuildConfig.DEBUG) Level.BASIC else Level.NONE
        builder.addInterceptor(HttpLoggingInterceptor().apply { level = loggingLevel })
        return builder.build()
    }
}