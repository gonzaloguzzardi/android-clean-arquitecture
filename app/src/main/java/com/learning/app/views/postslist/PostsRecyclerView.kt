package com.learning.app.views.postslist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.data.model.PostItemData

class PostsRecyclerView(context: Context, attrs: AttributeSet? = null) :
    RecyclerView(context, attrs) {

    init {
        overScrollMode = OVER_SCROLL_NEVER
        layoutManager = LinearLayoutManager(context)
    }

    fun setData() {
        adapter = PostsAdapter(
            listOf(
                PostItemData("Title", "author", "25m"),
                PostItemData("Title", "author", "15m")
            )
        )
    }
}