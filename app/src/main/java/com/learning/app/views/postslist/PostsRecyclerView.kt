package com.learning.app.views.postslist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.domain.model.PostItemDomainModel


class PostsRecyclerView(context: Context, attrs: AttributeSet? = null) :
    RecyclerView(context, attrs) {

    interface OnItemRemovedListener {
        fun onRemoved(postId: String)
    }

    private val itemTouchCallback = PostTouchHelperCallback(this)

    init {
        setHasFixedSize(true)
        overScrollMode = OVER_SCROLL_NEVER
        layoutManager = LinearLayoutManager(context)
        val itemDecorator = DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL)
        addItemDecoration(itemDecorator)
        setItemTouchCallback()
    }


    fun setData(posts: List<PostItemDomainModel>, listener: OnItemRemovedListener) {
        adapter = PostsAdapter(posts.toMutableList(), listener)
    }

    private fun setItemTouchCallback() {
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(this)
    }
}