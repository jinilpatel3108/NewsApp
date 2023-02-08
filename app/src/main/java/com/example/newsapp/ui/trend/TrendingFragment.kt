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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.NewsAdapter
import com.example.newsapp.data.NewsItemClicked
import com.example.newsapp.databinding.FragmentTrendingBinding
import com.example.newsapp.model.News
import com.example.newsapp.SingleNews
import com.example.newsapp.utils.Utils

class TrendingFragment : Fragment(), NewsItemClicked{

    private var _binding: FragmentTrendingBinding? = null
    private lateinit var progressBar: ProgressBar
    private val binding get() = _binding!!
    private lateinit var mAdapter: NewsAdapter
    private lateinit var trendingViewModel : TrendingViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTrendingBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recyclerView: RecyclerView = binding.newsRecycler
        val categorySpinner: Spinner = binding.categryDropDwn
        val countrySpinner: Spinner = binding.countryDropDwn

        progressBar = binding.progressCircular

        trendingViewModel.trendNews.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                mAdapter.updateNews(it)
            }
        })
        recyclerViewAdapter(recyclerView)
        categorySpinnerMethod(categorySpinner)
        countrySpinnerMethod(countrySpinner)

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trendingViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[TrendingViewModel::class.java]

        mAdapter = NewsAdapter(this)
        trendingViewModel.fetchData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun recyclerViewAdapter(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter =mAdapter
    }

    private fun categorySpinnerMethod(categorySpinner: Spinner){
        categorySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {
                trendingViewModel.selectedCategory = parent.selectedItem.toString()
                trendingViewModel.fetchData()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun countrySpinnerMethod(countrySpinner: Spinner) {
        countrySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {
                trendingViewModel.selectedCountry = Utils.countryMap[parent.selectedItem.toString()].toString()
                trendingViewModel.fetchData()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    override fun onItemClicked(item: News) {
        val intent = Intent(context, SingleNews::class.java)
        intent.putExtra("News", item)
        startActivity(intent)
    }
}