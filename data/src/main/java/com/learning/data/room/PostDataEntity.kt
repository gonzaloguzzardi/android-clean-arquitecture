package com.learning.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.learning.domain.model.PostItemDomainModel

@Entity(tableName = "posts")
data class PostDataEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String?,
    val storyTitle: String?,
    val author: String?,
    val creationTime: String?
) {

    fun toDomainModel() =
        PostItemDomainModel(id, title ?: storyTitle, author, creationTime)
}