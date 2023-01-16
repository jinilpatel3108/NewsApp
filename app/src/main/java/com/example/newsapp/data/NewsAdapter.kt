package com.example.newsapp.data

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.News
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: MutableList<News> = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent,false)

        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[(viewHolder.absoluteAdapterPosition)])
        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title

        holder.descriptionView.text = currentItem.description

        holder.sourceView.text = currentItem.name

        val parsedDate = LocalDateTime.parse(currentItem.publishedAt, DateTimeFormatter.ISO_DATE_TIME)
        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))

        holder.dateView.text = formattedDate
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews: ArrayList<News>) {
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