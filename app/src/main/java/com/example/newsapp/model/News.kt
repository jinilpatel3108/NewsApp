package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import java.io.Serializable

@Entity(
    tableName = "news_db"
)

data class News(
    val title: String,
    val description: String,
    val source: Source,
    val publishedAt: String,
    @SerialName("urlToImage") val urlToImage: String,
    @PrimaryKey val url: String,
) : Serializable

data class Source(
    @SerializedName("name")
    val name: String
) : Serializable