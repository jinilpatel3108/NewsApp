package com.example.newsapp.db

import androidx.lifecycle.LiveData
import com.example.newsapp.model.News

class NewsRepository(private val newsDao: NewsDao) {

    val allNews : LiveData<List<News>> = newsDao.getAllNews()

    suspend fun insert(news: News) {
        newsDao.insertNews(news)
    }

    suspend fun delete(news: News) {
        newsDao.deleteNews(news)
    }

}