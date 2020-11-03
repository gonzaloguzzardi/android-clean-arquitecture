package com.learning.data.retrofit.responses

import com.learning.data.model.PostItemDataModel
import com.squareup.moshi.Json

data class GetPostsResponse(@field:Json(name = "hits") val posts: List<PostItemDataModel>)