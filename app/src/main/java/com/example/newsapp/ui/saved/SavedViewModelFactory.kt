package com.example.newsapp.ui.saved

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SavedViewModelFactory( private val application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedViewModel::class.java)) {
            return SavedViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}