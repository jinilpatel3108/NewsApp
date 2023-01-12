package com.example.newsapp.ui.trend

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.data.NewsAdapter
import com.example.newsapp.data.NewsContainer
import com.example.newsapp.data.NewsItemClicked
import com.example.newsapp.databinding.FragmentTrendingBinding
import com.example.newsapp.model.News
import okhttp3.internal.Internal.instance

class TrendingFragment : Fragment(), NewsItemClicked{

    private var _binding: FragmentTrendingBinding? = null

    private val binding get() = _binding!!

    private lateinit var mAdapter: NewsAdapter

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

    private fun fetchData() {
        val BASE_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=66ac55a588a94186be5858f7672344fb"

        val jsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET,
            BASE_URL,
            null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")

                val newsArray = ArrayList<News>()

                for(i in 0 until newsJsonArray.length()) {
                    val newJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newJsonObject.getString("title"),
                        newJsonObject.getString("description"),
                        newJsonObject.getJSONObject("source").getString("name"),
                        newJsonObject.getString("publishedAt"),
                        newJsonObject.getString("urlToImage"),
                        newJsonObject.getString("url")
                    )

                    newsArray.add(news)
                }

                mAdapter.updateNews(newsArray)
            },

            Response.ErrorListener {
                System.out.println("Erorr: ")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        context?.let { NewsContainer.getInstance(it).addToRequestQueue(jsonObjectRequest) }

    }

    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()

        context?.let { customTabsIntent.launchUrl(it, Uri.parse(item.url)) }
    }


}