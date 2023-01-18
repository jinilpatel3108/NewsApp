package com.example.newsapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class News(
    val title: String,
    val description: String,
    val source: Source,
    val publishedAt: String,
    @SerialName("urlToImage") val urlToImage: String,
    val url: String
)


data class Source(
    @SerializedName("name")
    val name: String
)