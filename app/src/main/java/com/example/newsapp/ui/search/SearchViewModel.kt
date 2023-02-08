package com.example.newsapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.News
import com.example.newsapp.model.Response
import com.example.newsapp.utils.APIInterface
import com.example.newsapp.utils.ApiClient
import com.example.newsapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback

class SearchViewModel(): ViewModel() {

    var searchNews : MutableLiveData<List<News>> = MutableLiveData<List<News>>()
    var queryString: MutableLiveData<String> = MutableLiveData<String>()

    init {
        fetchData()
    }

    fun fetchData(){
        val apiService: APIInterface = ApiClient.getClient().create(APIInterface::class.java)
        val call : Call<Response>? = queryString?.let { it.value?.let { it1 ->
            apiService.getSearchedNews(
                it1, Utils.API_KEY)
        } }

        call?.enqueue(object: Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                if(response.body()?.status.equals("ok")) {
                    val articleList : List<News> = response.body()!!.news
                    if(articleList.isNotEmpty()) {
                        searchNews.apply { postValue(articleList) }
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {

            }
        })
    }



}