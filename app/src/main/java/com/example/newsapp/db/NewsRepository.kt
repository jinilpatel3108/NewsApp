package com.example.newsapp.db

import androidx.lifecycle.LiveData
import com.example.newsapp.model.News
import retrofit2.Retrofit

class NewsRepository (

    val newsDao: NewsDao
)
{
    val allnews: LiveData<List<News>> = newsDao.getAllNews()
    suspend fun insert(news: News) = newsDao.insertNews(news)


}