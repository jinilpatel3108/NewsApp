package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.newsapp.databinding.ActivitySingleNewsBinding

class SingleNews : AppCompatActivity(){

    private lateinit var binding: ActivitySingleNewsBinding

    private lateinit var webview: WebView
    private lateinit var ss : String

    private lateinit var toolbar: Toolbar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ss = intent.getStringExtra("UrlValue").toString()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.single_news_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.navigation_save -> {
                saveData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveData() {
        Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
    }

}