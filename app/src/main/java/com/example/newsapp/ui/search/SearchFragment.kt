package com.example.newsapp.ui.search

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.SingleNews
import com.example.newsapp.data.NewsAdapter
import com.example.newsapp.data.NewsItemClicked
import com.example.newsapp.databinding.FragmentSearchBinding
import com.example.newsapp.model.News

class SearchFragment : Fragment() , NewsItemClicked{

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: NewsAdapter
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchView: SearchView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recyclerView: RecyclerView = binding.newsRecyclerSearched
        searchView = binding.searchBar

        recyclerViewAdapter(recyclerView)
        searchViewMethod(searchView)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.searchNews.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                mAdapter.updateNews(it)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun recyclerViewAdapter(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = NewsAdapter(this)
        recyclerView.adapter =mAdapter
        searchViewModel.fetchData()
    }

    private fun searchViewMethod(searchView: SearchView){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchViewModel.setQuery(query)
                    searchViewModel.fetchData()
                }
                return false
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel= ViewModelProvider(this)[SearchViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(item: News) {
        val intent = Intent(context, SingleNews::class.java)
        intent.putExtra("News", item)
        startActivity(intent)
    }

}