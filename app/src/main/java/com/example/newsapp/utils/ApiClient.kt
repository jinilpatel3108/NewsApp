package com.example.newsapp.utils

import com.example.newsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ApiClient {

    companion object {
        private const val BASE_URL: String = "https://newsapi.org/v2/"
        private val retrofit: APIInterface? = null

        fun getInstance(): APIInterface {
            if (retrofit == null) {
                return Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIInterface::class.java)
            }
            return retrofit
        }
    }
}

interface APIInterface {

    @GET("top-headlines")
    fun getLatestNewsByCountry(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = Utils.API_KEY
    ): Call<NewsResponse>

    @GET("top-headlines")
    fun getLatestNewsByCategoryAndCountry(
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = Utils.API_KEY
    ): Call<NewsResponse>

    @GET("everything")
    fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = Utils.API_KEY
    ): Call<NewsResponse>
}