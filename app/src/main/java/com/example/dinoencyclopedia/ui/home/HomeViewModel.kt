package com.example.dinoencyclopedia.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dinoencyclopedia.data.remote.encyclopedia.EncyclopediaRepository
import com.example.dinoencyclopedia.data.remote.news.NewsRepository
import com.example.dinoencyclopedia.domain.news.NewsCategory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private val newsRepository: NewsRepository,
    private val encyclopediaRepository: EncyclopediaRepository
) : ViewModel() {
    private val _homeUiState = MutableLiveData(HomeUiState())
    val homeUiState: LiveData<HomeUiState> = _homeUiState

    private val compositeDisposable = CompositeDisposable()

    init {
        loadNews()
        loadDinosaurOfTheDay()
    }

    private fun loadNews() {
        _homeUiState.value = _homeUiState.value?.copy(isLoading = true)

        newsRepository.fetchNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { news ->
                    val categorizedNews = news.map { article ->
                        val text = "${article.title.orEmpty()} ${article.description.orEmpty()}"
                        article.copy(category = article.category.detectNewsCategory(text))
                    }

                    _homeUiState.value = _homeUiState.value?.copy(
                        news = categorizedNews,
                        isLoading = false
                    )
                },
                { error ->
                    _homeUiState.value = _homeUiState.value?.copy(isLoading = false)
                    Log.e(TAG, "Error loading news: ${error.message}")
                }
            )
            .addTo(compositeDisposable)
    }

    fun loadDinosaurOfTheDay() {
        encyclopediaRepository.getDinosaurOfTheDay()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dinosaur ->
                    Log.e(TAG, "Loaded dino: $dinosaur")
                    _homeUiState.value =
                        _homeUiState.value?.copy(dinosaurOfTheDay = dinosaur)
                },
                { error ->
                    Log.e(TAG, "Error loading dinosaur of the day")
                }
            )
            .addTo(compositeDisposable)
    }

    fun filterByCategory(newsCategory: NewsCategory) {
        _homeUiState.value = _homeUiState.value?.copy(selectedCategory = newsCategory)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}