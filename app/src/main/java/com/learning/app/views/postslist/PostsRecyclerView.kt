package com.learning.app.views.postslist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.domain.model.PostItemDomainModel

class PostsRecyclerView(context: Context, attrs: AttributeSet? = null) :
    RecyclerView(context, attrs) {

    init {
        setHasFixedSize(true)
        overScrollMode = OVER_SCROLL_NEVER
        layoutManager = LinearLayoutManager(context)
    }

    fun setData(posts: List<PostItemDomainModel>) {
        adapter = PostsAdapter(posts)
    }
}