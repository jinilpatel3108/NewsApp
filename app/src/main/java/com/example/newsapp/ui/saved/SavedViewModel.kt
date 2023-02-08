package com.example.newsapp.ui.saved

import android.app.Application
import androidx.lifecycle.*
import com.example.newsapp.db.ArticleDB
import com.example.newsapp.db.NewsRepository
import com.example.newsapp.model.News


class SavedViewModel(application: Application): ViewModel() {

    var allNews : LiveData<List<News>>
    private var repository: NewsRepository

    init {
        val dao = ArticleDB.getInstance(application).getArticleDao()
        repository = NewsRepository(dao)
        allNews = repository.allnews
    }
}