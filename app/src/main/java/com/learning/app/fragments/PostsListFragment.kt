package com.learning.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.learning.app.databinding.FragmentPostsListBinding

class PostsListFragment : Fragment() {

    private lateinit var binding: FragmentPostsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsListBinding.inflate(inflater, container, false)
        binding.swipeRefreshLayout.setOnRefreshListener {
            // TODO refresh
        }
        binding.recyclerViewPosts.setData()
        return binding.root
    }
}