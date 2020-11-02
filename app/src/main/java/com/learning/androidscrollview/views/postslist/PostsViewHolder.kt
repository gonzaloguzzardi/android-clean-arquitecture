package com.learning.androidscrollview.views.postslist

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.learning.androidscrollview.databinding.ViewHolderPostItemBinding
import com.learning.androidscrollview.extensions.setTextOrHide
import com.learning.data.model.PostItem

class PostsViewHolder(private val binding: ViewHolderPostItemBinding) : ViewHolder(binding.root) {

    companion object {

        private const val SUBTITLE_STRINGS_SEPARATOR = " - "

        fun create(parent: ViewGroup): ViewHolder {
            val binding = ViewHolderPostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return PostsViewHolder(binding)
        }
    }

    fun bind(postItem: PostItem) {
        binding.postTitle.setTextOrHide(postItem.title)
        bindSubtitle(postItem)
    }

    private fun bindSubtitle(postItem: PostItem) {
        if (postItem.author.isNullOrBlank() && postItem.creationTime.isNullOrBlank()) {
            binding.postSubtitle.visibility = GONE
        } else {
            val subtitle = createSubtitle(postItem.author, postItem.creationTime)
            binding.postSubtitle.text = subtitle
            binding.postSubtitle.visibility = VISIBLE
        }
    }

    private fun createSubtitle(author: String?, creationTime: String?): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder()
        if (!author.isNullOrBlank()) {
            spannableStringBuilder.append(author)
        }
        if (!creationTime.isNullOrBlank()) {
            spannableStringBuilder.append(SUBTITLE_STRINGS_SEPARATOR)
            spannableStringBuilder.append(getTimeSinceCreation(creationTime))
        }
        return spannableStringBuilder
    }

    private fun getTimeSinceCreation(creationTime: String): String {
        // TODO implement
        return creationTime
    }
}