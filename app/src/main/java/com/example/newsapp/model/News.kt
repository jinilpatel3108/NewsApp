package com.example.newsapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class News(
    val title: String,
    val description: String,
    val name: String,
    val publishedAt: String,
    @SerialName("urlToImage") val urlToImage: String,
    val url: String
)
