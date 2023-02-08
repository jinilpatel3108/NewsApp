package com.example.newsapp.ui.trend

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.News
import com.example.newsapp.model.Response
import com.example.newsapp.utils.APIInterface
import com.example.newsapp.utils.ApiClient
import com.example.newsapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback

@RequiresApi(Build.VERSION_CODES.O)
class TrendingViewModel() : ViewModel() {

    var selectedCategory: String = "All"
    var selectedCountry: String = "in"
    var trendNews : MutableLiveData<List<News>> = MutableLiveData<List<News>>()

    init {
        fetchData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchData() {

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
                        trendNews.apply { postValue(articleList) }
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
            }
        })
    }

}