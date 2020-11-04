package com.learning.data.model

import com.learning.data.room.posts.PostDataEntity
import com.learning.domain.model.PostItemDomainModel
import com.squareup.moshi.Json

data class PostItemDataModel(
    @field:Json(name = "objectID") val id: String?,
    val title: String?,
    @field:Json(name = "story_title") val storyTitle: String?,
    val author: String?,
    @field:Json(name = "created_at") val creationTime: String?,
    @field:Json(name = "story_url") val storyUrl: String?,
)

fun PostItemDataModel.toDomainModel(): PostItemDomainModel =
    PostItemDomainModel(id, title ?: storyTitle, author, creationTime, storyUrl)

fun PostItemDataModel.toEntity(): PostDataEntity =
    PostDataEntity(id ?: "", title, storyTitle, author, creationTime, storyUrl)