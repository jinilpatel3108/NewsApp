package com.example.newsapp.ui.trend

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.NewsAdapter
import com.example.newsapp.data.NewsItemClicked
import com.example.newsapp.databinding.FragmentTrendingBinding
import com.example.newsapp.model.News
import com.example.newsapp.model.Response
import com.example.newsapp.SingleNews
import com.example.newsapp.utils.APIInterface
import com.example.newsapp.utils.ApiClient
import com.example.newsapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback


class TrendingFragment : Fragment(), NewsItemClicked{

    private var _binding: FragmentTrendingBinding? = null
    private lateinit var prog: ProgressBar
    private val binding get() = _binding!!
    private var selectedCategory: String = "All"
    private var selectedCountry: String = "in"
    private lateinit var mAdapter: NewsAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTrendingBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recyclerView: RecyclerView = binding.newsRecycler
        var categorySpinner: Spinner = binding.categryDropDwn
        var countrySpinner: Spinner = binding.countryDropDwn

        prog = binding.progressCircular

        recyclerViewAdapter(recyclerView)

        categorySpinnerMethod(categorySpinner)

        countrySpinnerMethod(countrySpinner)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun recyclerViewAdapter(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(context)
        fetchData()
        mAdapter = NewsAdapter(this)
        recyclerView.adapter =mAdapter
    }

    private fun categorySpinnerMethod(categorySpinner: Spinner){

        val categories = resources.getStringArray(R.array.category_array)

        val adapter = context?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, categories)
        }

        categorySpinner.adapter = adapter

        categorySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {
                selectedCategory = categories[position]
                fetchData()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun countrySpinnerMethod(countrySpinner: Spinner) {
        val countries = resources.getStringArray(R.array.country_array)
        val countryAdapter = context?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, countries)
        }
        countrySpinner.adapter = countryAdapter

        countrySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {
                selectedCountry = Utils.countryMap[countries[position]].toString()
                fetchData()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchData() {

        prog.visibility = View.VISIBLE
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
                        prog.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(context, "News Can't be Fetched!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClicked(item: News) {
        val intent = Intent(context, SingleNews::class.java)
        intent.putExtra("News", item)
        startActivity(intent)
    }
}