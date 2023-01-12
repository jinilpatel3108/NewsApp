package com.example.newsapp
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.newsapp.ui.trend.TrendingFragment

class VolleyFetcher(context: TrendingFragment){

    companion object{
        @Volatile
        private var INSTANCE: VolleyFetcher? = null
        fun getInstance(context: TrendingFragment) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: VolleyFetcher(
                    context
                ).also { INSTANCE = it }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.context?.applicationContext ?: null)
    }

    fun <T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }

}