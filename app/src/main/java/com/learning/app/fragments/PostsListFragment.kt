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
import com.learning.app.utils.network.NetworkState
import com.learning.app.viewmodels.PostsListViewModel
import com.learning.app.viewmodels.PostsListViewModelFactory
import com.learning.app.views.postslist.PostsRecyclerView
import com.learning.domain.model.PostItemDomainModel

class PostsListFragment : Fragment(), PostsRecyclerView.OnItemRemovedListener {

    private val postsViewModel: PostsListViewModel by viewModels { PostsListViewModelFactory() }

    private var binding: FragmentPostsListBinding? = null

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

        postsViewModel.postsViewStateLiveData.observe(viewLifecycleOwner, stateObserver)

        setSwipeRefreshListener()

        if (!postsViewModel.hasPostsLoaded()) {
            loadPosts()
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onRemoved(postId: String) {
        postsViewModel.deletePost(postId)
    }

    private fun loadPosts() {
        postsViewModel.loadPosts(NetworkState.isNetworkConnected)
    }

    private fun setSwipeRefreshListener() {
        binding?.let { binding->
            binding.swipeRefreshLayout.setOnRefreshListener {
                loadPosts()
            }
        }
    }

    private fun showError() {
        binding?.let { binding->
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun showSuccess(posts: List<PostItemDomainModel>) {
        binding?.let { binding ->
            binding.swipeRefreshLayout.isRefreshing = false
            if (posts.isNotEmpty()) {
                binding.recyclerViewPosts.setData(posts, this)
            }
        }
    }

    private fun showLoading() {
        binding?.let { binding ->
            binding.swipeRefreshLayout.let { swipeRefreshLayout ->
                swipeRefreshLayout.post {
                    swipeRefreshLayout.isRefreshing = postsViewModel.isLoadingPosts()
                }
            }
        }
    }
}