package com.example.newsapp.utils

import com.example.newsapp.model.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ApiClient {

    companion object {
        public val BASE_URL : String = "https://newsapi.org/v2/"
        private val retrofit : Retrofit? = null

        public fun getClient(): Retrofit {

            if (retrofit==null) {
                return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit;
        }

    }

}

public interface APIInterface {
    @GET("top-headlines")
    fun getLatestNews(@Query("country") source: String, @Query("apiKey") apiKey:String) : Call<Response>

    @GET("everything")
    fun getSearchedNews(@Query("q") source: String, @Query("apiKey") apiKey:String) : Call<Response>
}