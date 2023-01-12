package com.example.newsapp.ui.saved

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSavedBinding
import com.example.newsapp.databinding.FragmentSearchBinding
import com.example.newsapp.ui.search.SearchViewModel

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null

    private val binding get() = _binding!!
// comment made
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val savedViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        _binding = FragmentSavedBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val textView: TextView = binding.textSaved
        savedViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

//    jiji

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}