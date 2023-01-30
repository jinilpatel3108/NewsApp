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
import com.example.newsapp.db.ArticleDB
import com.example.newsapp.model.News
import com.example.newsapp.model.Source
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable

class SingleNews : AppCompatActivity(){

    private lateinit var binding: ActivitySingleNewsBinding

    private lateinit var webview: WebView
    private lateinit var urlValue : String

    private lateinit var toolbar: Toolbar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userInfo = intent.getSerializableExtra("News") as News
        urlValue = userInfo.url

        binding = ActivitySingleNewsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        webview = binding.webView
        toolbar = binding.toolbar

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webview.settings.javaScriptEnabled = true

        webview.apply {
            loadUrl(urlValue)
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

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveData() {

        val userInfo = intent.getSerializableExtra("News") as News

        GlobalScope.launch(Dispatchers.IO) {
            val result = ArticleDB.getInstance(this@SingleNews).
            getArticleDao().insertNews(userInfo)

            if(result>0)
            {
                Toast.makeText(this@SingleNews,"Saved Successfully", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this@SingleNews,"Error Occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

