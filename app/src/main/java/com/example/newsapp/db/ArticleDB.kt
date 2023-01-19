package com.example.newsapp.db

import android.content.Context
import androidx.room.*
import com.example.newsapp.model.News

@Database(
    entities = [News::class],
    version = 3
)
@TypeConverters(Converters::class)

abstract class ArticleDB : RoomDatabase(){

    abstract fun getArticleDao(): NewsDao

    companion object{
        private var instance: ArticleDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDB::class.java,
                "article_db.db"
            ).build()
    }

}