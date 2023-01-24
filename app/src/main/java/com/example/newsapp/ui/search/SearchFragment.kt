package com.example.newsapp.ui.search

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.SingleNews
import com.example.newsapp.data.NewsAdapter
import com.example.newsapp.data.NewsItemClicked
import com.example.newsapp.databinding.FragmentSearchBinding
import com.example.newsapp.model.News
import com.example.newsapp.model.Response
import com.example.newsapp.utils.APIInterface
import com.example.newsapp.utils.ApiClient
import com.example.newsapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback

class SearchFragment : Fragment() , NewsItemClicked{

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private lateinit var mAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recyclerView: RecyclerView = binding.newsRecyclerSearched
        val searchView: SearchView = binding.searchBar

        recyclerView.layoutManager = LinearLayoutManager(context)

        mAdapter = NewsAdapter(this)
        recyclerView.adapter =mAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextSubmit(query: String?): Boolean {
                fetchData(query)
                return false
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchData(query: String?) {
        val apiService: APIInterface = ApiClient.getClient().create(APIInterface::class.java)
        val call : Call<Response>? = query?.let { apiService.getSearchedNews(it, Utils.API_KEY) }

        call?.enqueue(object: Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                if(response.body()?.status.equals("ok")) {
                    val articleList : List<News> = response.body()!!.news
                    if(articleList.isNotEmpty()) {
                        mAdapter.updateNews(articleList)
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onItemClicked(item: News) {
        val intent = Intent(context, SingleNews::class.java)
        intent.putExtra("Title", item.title)
        intent.putExtra("Description", item.description)
        intent.putExtra("name", item.source.name)
        intent.putExtra("publishedDate", item.publishedAt)
        intent.putExtra("UrlValue", item.url)
        intent.putExtra("urlToImage", item.urlToImage)

        startActivity(intent)
    }

}