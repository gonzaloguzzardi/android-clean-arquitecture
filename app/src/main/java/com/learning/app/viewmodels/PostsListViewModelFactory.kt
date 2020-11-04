package com.learning.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learning.app.application.MainApplication
import com.learning.data.repositories.PostsRepositoryImpl
import com.learning.data.retrofit.services.PostsRetrofitService
import com.learning.domain.usecases.GetPostsUseCase

class PostsListViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PostsListViewModel(
        GetPostsUseCase(
            PostsRepositoryImpl(
                PostsRetrofitService.create(),
                MainApplication.database.postsDao()
            )
        )
    ) as T
}