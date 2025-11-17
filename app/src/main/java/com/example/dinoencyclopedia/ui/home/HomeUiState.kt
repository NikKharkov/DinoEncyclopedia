package com.example.dinoencyclopedia.ui.home

import com.example.dinoencyclopedia.data.local.DinosaurEntity
import com.example.dinoencyclopedia.domain.news.NewsArticle
import com.example.dinoencyclopedia.domain.news.NewsCategory

data class HomeUiState(
    val news: List<NewsArticle> = emptyList(),
    val selectedCategory: NewsCategory = NewsCategory.ALL,
    val dinosaurOfTheDay: DinosaurEntity? = null,
    val isLoading: Boolean = false
)