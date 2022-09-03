package com.example.passion.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.passion.data.models.Articles
import com.example.passion.databinding.ItemNewsLayoutBinding


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var articles = mutableListOf<Articles>()

    fun setNewsArticles(items: List<Articles>) {
        articles = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsLayoutBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = articles.size

   inner class NewsViewHolder(private val itemBinding: ItemNewsLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(pos: Int) {
            val article = articles[pos]
            article.let {
                itemBinding.tvTitleNews.text = it.title
                itemBinding.tvDescriptionNews.text = it.description
                if(it.urlToImage!=null)
                Glide.with(itemView.context)
                    .load(Uri.parse(it.urlToImage))
                    .apply(RequestOptions.circleCropTransform())
                    .into(itemBinding.imageViewNews)
            }
        }
    }
}
