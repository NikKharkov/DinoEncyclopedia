package com.example.dinoencyclopedia.domain.news

data class NewsArticle(
    val title: String?,
    val description: String?,
    val link: String?,
    val pubDate: String?,
    val imageUrl: String? = null,
    val category: NewsCategory = NewsCategory.ALL
) {
    fun getPlaceholderForArticle(): Int {
        return category.placeholderRes
    }
}