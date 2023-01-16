package com.example.newsapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R

class SingleNews : AppCompatActivity(){

    private lateinit var webview: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_news)

        val ss:String = intent.getStringExtra("UrlValue").toString()
        webview = findViewById(R.id.webView)
        webview.settings.setJavaScriptEnabled(true)

        webview.apply {
            loadUrl(ss)
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

    }
}