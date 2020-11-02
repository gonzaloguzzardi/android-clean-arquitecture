package com.learning.androidscrollview.views.postslist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.data.model.PostItem

class PostsRecyclerView(context: Context, attrs: AttributeSet? = null) :
    RecyclerView(context, attrs) {

    init {
        layoutManager = LinearLayoutManager(context)
    }

    fun setData() {
        adapter = PostsAdapter(
            listOf(
                PostItem("Title", "author", "25m"),
                PostItem("Title", "author", "15m")
            )
        )
    }

}