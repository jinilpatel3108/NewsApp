package com.example.newsapp.ui.saved

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.newsapp.db.ArticleDB
import com.example.newsapp.db.NewsRepository
import com.example.newsapp.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SavedViewModel(application: Application): AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    var allNews : LiveData<List<News>>
    private var repository: NewsRepository

    init {
        val dao = ArticleDB.getInstance(application).getArticleDao()
        repository = NewsRepository(dao)
        allNews = repository.allnews
    }

    fun insertNews(news: News) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(news)
    }

    fun deleteNews(news: News) = viewModelScope.launch(Dispatchers.IO) {
        repository.newsDao
    }

}