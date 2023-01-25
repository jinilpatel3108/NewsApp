package com.example.newsapp.db

import androidx.lifecycle.LiveData
import com.example.newsapp.model.News

class NewsRepository (val newsDao: NewsDao) {
    val allnews: LiveData<List<News>> = newsDao.getAllNews()
}