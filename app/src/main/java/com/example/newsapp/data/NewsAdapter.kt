package com.example.newsapp.data

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.News
import com.example.newsapp.utils.Utils

class NewsAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: MutableList<News> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.descriptionView.text = currentItem.description
        holder.sourceView.text = currentItem.source.name
        holder.dateView.text = Utils.dateFormatChanger(currentItem.publishedAt)
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews: List<News>) {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.titleNews)
    val imageView: ImageView = itemView.findViewById(R.id.imageNews)
    val dateView: TextView = itemView.findViewById(R.id.publishedNews)
    val descriptionView: TextView = itemView.findViewById(R.id.descriptionNews)
    val sourceView: TextView = itemView.findViewById(R.id.sourceNews)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}