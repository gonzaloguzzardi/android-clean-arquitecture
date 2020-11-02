package com.learning.androidscrollview.views.postslist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.learning.data.model.PostItem

class PostsAdapter(private val posts: List<PostItem>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostsViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postItem = posts[position]
        (holder as PostsViewHolder).bind(postItem)
    }

    override fun getItemCount() = posts.size
}