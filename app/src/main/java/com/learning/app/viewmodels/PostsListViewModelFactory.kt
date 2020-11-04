package com.learning.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learning.app.application.MainApplication
import com.learning.data.repositories.PostsRepositoryImpl
import com.learning.data.retrofit.services.PostsRetrofitService
import com.learning.domain.usecases.DeletePostUseCase
import com.learning.domain.usecases.GetPostsUseCase

class PostsListViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  {
        val repository = PostsRepositoryImpl(
            PostsRetrofitService.create(),
            MainApplication.postsDatabase.postsDao(),
            MainApplication.deletedPostsDatabase.deletedPostsDao()
        )
        @Suppress("UNCHECKED_CAST")
        return PostsListViewModel(GetPostsUseCase(repository), DeletePostUseCase(repository)) as T
    }
}