package com.learning.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.learning.app.databinding.FragmentPostsListBinding
import com.learning.app.livedata.PostsLiveData
import com.learning.app.viewmodels.PostsListViewModel
import com.learning.domain.model.PostItemDomainModel

class PostsListFragment : Fragment() {

    private val postsViewModel: PostsListViewModel by viewModels()

    private lateinit var binding: FragmentPostsListBinding

    private val stateObserver = Observer<PostsLiveData.PostsViewState> { viewState ->
        if (viewState.isLoading) {
            showLoading()
        }
        if (viewState.isError) {
            showError()
        } else {
            showSuccess(viewState.posts)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsListBinding.inflate(inflater, container, false)

        binding.swipeRefreshLayout.setOnRefreshListener {
            // TODO refresh
        }
        postsViewModel.postsViewStateLiveData.observe(viewLifecycleOwner, stateObserver)
        postsViewModel.loadPosts()
        return binding.root
    }

    private fun showError() {

    }

    private fun showSuccess(posts: List<PostItemDomainModel>) {
        binding.recyclerViewPosts.setData(posts)
    }

    private fun showLoading() {

    }
}