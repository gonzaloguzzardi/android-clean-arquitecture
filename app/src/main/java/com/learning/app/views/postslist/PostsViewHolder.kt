package com.learning.app.views.postslist

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.learning.app.databinding.ViewHolderPostItemBinding
import com.learning.app.extensions.setTextOrHide
import com.learning.app.utils.getTimeSinceCreationString
import com.learning.domain.model.PostItemDomainModel

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

    fun bind(postItemModel: PostItemDomainModel) {
        binding.postTitle.setTextOrHide(postItemModel.title)
        bindSubtitle(postItemModel)
    }

    private fun bindSubtitle(postItemModel: PostItemDomainModel) {
        if (postItemModel.author.isNullOrBlank() && postItemModel.creationTime.isNullOrBlank()) {
            binding.postSubtitle.visibility = GONE
        } else {
            val subtitle = createSubtitle(
                postItemModel.author,
                getTimeSinceCreation(postItemModel.creationTime)
            )
            binding.postSubtitle.text = subtitle
            binding.postSubtitle.visibility = VISIBLE
        }
    }

    private fun createSubtitle(author: String?, timeSinceCreation: String?): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder()
        if (!author.isNullOrBlank()) {
            spannableStringBuilder.append(author)
        }
        if (!timeSinceCreation.isNullOrBlank()) {
            spannableStringBuilder.append(SUBTITLE_STRINGS_SEPARATOR)
            spannableStringBuilder.append(timeSinceCreation)
        }
        return spannableStringBuilder
    }

    private fun getTimeSinceCreation(creationTime: String?) =
        getTimeSinceCreationString(binding.root.context, creationTime)
}