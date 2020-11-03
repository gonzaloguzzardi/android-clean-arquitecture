package com.learning.app.views.postslist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.learning.data.model.PostItemData

class PostsAdapter(private val postData: List<PostItemData>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostsViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postItem = postData[position]
        (holder as PostsViewHolder).bind(postItem)
    }

    override fun getItemCount() = postData.size
}