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

        fun getInstance(context: Context): ArticleDB{
            if(instance==null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDB::class.java,
                    "article_db.db"
                ).fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }
}