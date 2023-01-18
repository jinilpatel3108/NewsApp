package com.example.newsapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.newsapp.databinding.ActivitySingleNewsBinding

class SingleNews : AppCompatActivity(){

    private lateinit var binding: ActivitySingleNewsBinding

    private lateinit var webview: WebView


    private lateinit var toolbar: Toolbar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ss:String = intent.getStringExtra("UrlValue").toString()

        binding = ActivitySingleNewsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        webview = binding.webView
        toolbar = binding.toolbar

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        webview.settings.javaScriptEnabled = true

        webview.apply {
            loadUrl(ss)
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}