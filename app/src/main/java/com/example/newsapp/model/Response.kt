package com.example.newsapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class Response(
    val status: String,
    val totalResults: Int,
    @SerializedName("articles") val news: List<News>
)
