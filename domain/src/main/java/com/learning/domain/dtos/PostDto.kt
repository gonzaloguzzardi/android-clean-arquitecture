package com.learning.domain.dtos

import com.squareup.moshi.Json

data class PostDto(
    val title: String?,
    val storyTitle: String?,
    val author: String?,
    @Json(name = "created_at") val creationTime: String?
)