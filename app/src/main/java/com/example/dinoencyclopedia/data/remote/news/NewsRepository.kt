package com.example.dinoencyclopedia.data.remote.news

import com.example.dinoencyclopedia.domain.news.NewsArticle
import com.example.dinoencyclopedia.util.RSS_NEWS_URL
import com.prof18.rssparser.RssParser
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.rx3.rxSingle

class NewsRepository {
    private val parser = RssParser()

    fun fetchNews(): Single<List<NewsArticle>> {
        return rxSingle {
            val channel = parser.getRssChannel(RSS_NEWS_URL)
            val news = channel.items.map {
                NewsArticle(
                    title = it.title,
                    description = it.description,
                    link = it.link,
                    pubDate = it.pubDate,
                    imageUrl = it.image
                )
            }
            news
        }
    }
}