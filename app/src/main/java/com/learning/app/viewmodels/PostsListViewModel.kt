package com.learning.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.app.livedata.PostsLiveData
import com.learning.domain.usecases.GetPostsUseCase
import kotlinx.coroutines.launch

class PostsListViewModel(private val getPostsUseCase: GetPostsUseCase): ViewModel() {

    val postsViewStateLiveData = PostsLiveData()

    fun loadPosts() {
        postsViewStateLiveData.updateLoading()
        viewModelScope.launch {
            getPostsUseCase.execute().also { result ->
                when (result) {
                    is GetPostsUseCase.Result.Success -> if (result.data.isEmpty()) {
                        postsViewStateLiveData.updateError()
                    } else {
                        postsViewStateLiveData.updateSuccess(result.data)
                    }

                    is GetPostsUseCase.Result.Error -> postsViewStateLiveData.updateError()
                }
            }
        }
    }
}