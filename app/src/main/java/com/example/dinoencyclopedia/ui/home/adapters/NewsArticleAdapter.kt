package com.example.dinoencyclopedia.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoencyclopedia.databinding.ItemNewsCardBinding
import com.example.dinoencyclopedia.domain.news.NewsArticle

class NewsArticleAdapter(
    private val onArticleClick: (NewsArticle) -> Unit,
    private val onShareClick: (NewsArticle) -> Unit
) : RecyclerView.Adapter<NewsArticleAdapter.NewsArticleViewHolder>() {

    private var news = emptyList<NewsArticle>()

    inner class NewsArticleViewHolder(private val binding: ItemNewsCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: NewsArticle) {
            binding.articleTitle.text = article.title
            binding.articleDescription.text = article.description
            binding.articleMeta.text = article.pubDate
            binding.articleThumbnail.setImageResource(article.getPlaceholderForArticle())

            binding.root.setOnClickListener {
                onArticleClick(article)
            }

            binding.btnShare.setOnClickListener {
                onShareClick(article)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsArticleViewHolder {
        val binding = ItemNewsCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NewsArticleViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NewsArticleViewHolder,
        position: Int
    ) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int = news.size

    fun submitList(newsArticles: List<NewsArticle>) {
        news = newsArticles
        notifyDataSetChanged()
    }
}