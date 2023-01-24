package com.example.newsapp.model

import com.google.gson.annotations.SerializedName

class Response(
    val status: String,
    @SerializedName("articles") val news: List<News>
)
