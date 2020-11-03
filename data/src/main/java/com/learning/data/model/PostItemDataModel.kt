package com.learning.data.model

import com.learning.domain.model.PostItemDomainModel
import com.squareup.moshi.Json

data class PostItemDataModel(
    @Json(name = "objectID") val id: String?,
    val title: String?,
    val storyTitle: String?,
    val author: String?,
    @Json(name = "created_at") val creationTime: String?
)

fun PostItemDataModel.toDomainModel(): PostItemDomainModel =
    PostItemDomainModel(id, title ?: storyTitle, author, creationTime)