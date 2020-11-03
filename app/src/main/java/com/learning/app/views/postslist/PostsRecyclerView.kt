package com.learning.app.views.postslist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.data.model.PostItemDataModel

class PostsRecyclerView(context: Context, attrs: AttributeSet? = null) :
    RecyclerView(context, attrs) {

    init {
        setHasFixedSize(true)
        overScrollMode = OVER_SCROLL_NEVER
        layoutManager = LinearLayoutManager(context)
    }

    fun setData() {
        adapter = PostsAdapter(
            listOf(
                PostItemDataModel("0", "Title", null, "author", "25m"),
                PostItemDataModel("1", "Title", null, "author", "15m")
            )
        )
    }
}