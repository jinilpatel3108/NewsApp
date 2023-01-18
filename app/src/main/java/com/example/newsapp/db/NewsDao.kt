package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.News


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAndInsert(news: News): Long

    @Query("SELECT * FROM news_db")
    fun getAllArticles(): LiveData<List<News>>

    @Delete
    suspend fun deleteArticle(news: News)



//} NewsDao {
}