package com.example.newsapp.ui.trend

import android.app.Application
import android.os.Build
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.NewsAdapter
import com.example.newsapp.model.News
import com.example.newsapp.model.Response
import com.example.newsapp.utils.APIInterface
import com.example.newsapp.utils.ApiClient
import com.example.newsapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback

class TrendingViewModel(application: Application) : AndroidViewModel(application) {

    var selectedCategory: String = "All"
    var selectedCountry: String = "in"


    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchData(mAdapter: NewsAdapter) {

//        progressBar.visibility = View.VISIBLE
        val apiService: APIInterface = ApiClient.getClient().create(APIInterface::class.java)
        val call: Call<Response> = if(selectedCategory=="All") {
            apiService.getLatestNewsByCountry(selectedCountry, Utils.API_KEY)
        } else {
            apiService.getLatestNewsByCategoryAndCountry(selectedCategory, selectedCountry, Utils.API_KEY)
        }

        call.enqueue(object: Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                if(response.body()?.status.equals("ok")) {
                    val articleList : List<News> = response.body()!!.news
                    if(articleList.isNotEmpty()) {
                        mAdapter.updateNews(articleList)
//                        progressBar.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(getApplication(), "News Can't be Fetched.", Toast.LENGTH_SHORT).show()
            }
        })
    }

}