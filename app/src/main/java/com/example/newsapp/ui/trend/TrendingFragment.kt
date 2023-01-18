package com.example.newsapp.ui.trend

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.NewsAdapter
import com.example.newsapp.data.NewsItemClicked
import com.example.newsapp.databinding.FragmentTrendingBinding
import com.example.newsapp.model.News
import com.example.newsapp.model.Response
import com.example.newsapp.ui.SingleNews
import com.example.newsapp.utils.APIInterface
import com.example.newsapp.utils.ApiClient
import com.example.newsapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback


class TrendingFragment : Fragment(), NewsItemClicked{

    private var _binding: FragmentTrendingBinding? = null

    private val binding get() = _binding!!

    private lateinit var mAdapter: NewsAdapter
    private lateinit var webView: WebView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //temp
        val trendViewModel = ViewModelProvider(this).get(TrendingViewModel::class.java)
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recyclerView: RecyclerView = binding.newsRecycler

        recyclerView.layoutManager = LinearLayoutManager(context)

        fetchData()
        mAdapter = NewsAdapter(this)

        recyclerView.adapter =mAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchData() {
        val BASE_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=66ac55a588a94186be5858f7672344fb"

        val apiService: APIInterface = ApiClient.getClient().create<APIInterface>(APIInterface::class.java)

        val call : Call<Response> = apiService.getLatestNews("in",Utils.API_KEY);


        call.enqueue(object: Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                if(response.body()?.status.equals("ok")) {
                    val articleList : List<News> = response.body()!!.news;
                    if(articleList.isNotEmpty()) {
                        mAdapter.updateNews(articleList)
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                println("Array: Error")
            }
        })
    }

    override fun onItemClicked(item: News) {
        val intent = Intent(context, SingleNews::class.java)
        intent.putExtra("UrlValue", item.url)
        startActivity(intent)
    }
}