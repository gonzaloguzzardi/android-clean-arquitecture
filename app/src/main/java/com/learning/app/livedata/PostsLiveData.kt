package com.learning.app.livedata

import androidx.lifecycle.MutableLiveData
import com.learning.app.extensions.notifyObservers
import com.learning.domain.model.PostItemDomainModel

class PostsLiveData: MutableLiveData<PostsLiveData.PostsViewState>() {

    data class PostsViewState(
        var isLoading: Boolean = true,
        var isError: Boolean = false,
        var posts: List<PostItemDomainModel> = listOf()
    )

    fun updateSuccess(posts: List<PostItemDomainModel>) {
        if (value == null) {
            value = PostsViewState(
                isLoading = false,
                isError = false,
                posts = posts
            )
        } else {
            value?.apply {
                isLoading = false
                isError = false
                this.posts = posts
            }
            notifyObservers()
        }
    }

    fun updateLoading() {
        if (value == null) {
            value = PostsViewState(
                isLoading = true,
                isError = false,
                posts = emptyList()
            )
        } else {
            value?.apply {
                isLoading = true
                isError = false
            }
            notifyObservers()
        }
    }

    fun updateError() {
        if (value == null) {
            value = PostsViewState(
                isLoading = false,
                isError = true,
                posts = emptyList()
            )
        } else {
            value?.apply {
                isError = true
                isLoading = false
            }
            notifyObservers()
        }
    }
}