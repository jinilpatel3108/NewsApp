package com.example.newsapp.ui.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.NewsAdapter
import com.example.newsapp.data.NewsItemClicked
import com.example.newsapp.databinding.FragmentSavedBinding
import com.example.newsapp.model.News

class SavedFragment : Fragment(), NewsItemClicked {

    private var _binding: FragmentSavedBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSavedBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recyclerView: RecyclerView = binding.newsRecyclerSaved

        recyclerView.layoutManager = LinearLayoutManager(context)
        val mAdapter = NewsAdapter(this)
        recyclerView.adapter = mAdapter

        val savedViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(SavedViewModel::class.java)

        savedViewModel.allNews.observe(viewLifecycleOwner, Observer {list ->
            list?.let {
                println("Hello")
                mAdapter.updateNews(it)
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(item: News) {
        TODO("Not yet implemented")
    }

}