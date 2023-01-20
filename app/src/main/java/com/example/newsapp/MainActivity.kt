package com.example.newsapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.db.ArticleDB
import com.example.newsapp.model.News
import com.example.newsapp.model.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private lateinit var binding: ActivityMainBinding

    lateinit var database: ArticleDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar

        setSupportActionBar(toolbar)

//        database = Room.databaseBuilder(applicationContext,ArticleDB::class.java,"article_db").build()
//
//        database = ArticleDB.
//        GlobalScope.launch{
//            database.getArticleDao().insertNews(News(1,"jinil","patel", Source("jinil"),"19-01-2023","abc","patel"))
//        }
//        val usrinfo = News(1,"jinil","jinil",Source("jinil"),"20-01-2023","patel","idj")
//        GlobalScope.launch(Dispatchers.IO) {
//            ArticleDB.getInstance(this@MainActivity).getArticleDao().insertNews(usrinfo)
//        }


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_trend, R.id.navigation_saved, R.id.navigation_search
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}