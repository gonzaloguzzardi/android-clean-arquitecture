package com.learning.app.views.postslist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.learning.domain.model.PostItemDomainModel


class PostsAdapter(
    private val posts: MutableList<PostItemDomainModel>,
    private val onItemRemovedListener: PostsRecyclerView.OnItemRemovedListener?
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostsViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postItem = posts[position]
        (holder as PostsViewHolder).bind(postItem)
    }

    override fun getItemCount() = posts.size

    fun removeItem(position: Int) {
        val postId = posts[position].id
        posts.removeAt(position)
        notifyItemRemoved(position)
        if (postId != null) {
            onItemRemovedListener?.onRemoved(postId)
        }
    }
}