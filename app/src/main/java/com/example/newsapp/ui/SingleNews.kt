package com.example.newsapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.databinding.ActivitySingleNewsBinding

class SingleNews : AppCompatActivity(){

    private lateinit var binding: ActivitySingleNewsBinding

    private lateinit var webview: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ss:String = intent.getStringExtra("UrlValue").toString()

        binding = ActivitySingleNewsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        webview = binding.webView

        webview.settings.setJavaScriptEnabled(true)

        webview.apply {
            loadUrl(ss)
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

    }
}