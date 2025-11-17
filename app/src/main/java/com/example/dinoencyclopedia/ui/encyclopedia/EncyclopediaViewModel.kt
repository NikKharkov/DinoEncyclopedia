package com.example.dinoencyclopedia.ui.encyclopedia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dinoencyclopedia.data.local.DinosaurEntity
import com.example.dinoencyclopedia.data.remote.encyclopedia.EncyclopediaRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class EncyclopediaViewModel(
    private val encyclopediaRepository: EncyclopediaRepository
) : ViewModel() {

    private val _encyclopediaUiState = MutableLiveData(EncyclopediaUiState())
    val encyclopediaUiState: LiveData<EncyclopediaUiState> = _encyclopediaUiState

    private var allDinosaurs: List<DinosaurEntity> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    init {
        getAllDinosaurs()
        observeDinosaurs()
    }

    fun observeDinosaurs() {
        encyclopediaRepository.observeAllDinosaurs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dinosaurs ->
                    allDinosaurs = dinosaurs

                    if (_encyclopediaUiState.value?.showFavourites == false) {
                        _encyclopediaUiState.value =
                            _encyclopediaUiState.value?.copy(dinosaurs = dinosaurs)
                    } else {
                        _encyclopediaUiState.value =
                            _encyclopediaUiState.value?.copy(dinosaurs = dinosaurs.filter { it.isFavorite })
                    }
                },
                { error ->
                    Log.e(TAG, "Error observing dinosaurs: ${error.message}")
                }
            )
            .addTo(compositeDisposable)
    }

    fun getAllDinosaurs(forceToRefresh: Boolean = false) {
        _encyclopediaUiState.value = _encyclopediaUiState.value?.copy(isLoading = true)

        encyclopediaRepository.getAllDinosaurs(forceToRefresh)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dinosaurs ->
                    _encyclopediaUiState.value = _encyclopediaUiState.value?.copy(isLoading = false)
                },
                { error ->
                    _encyclopediaUiState.value = _encyclopediaUiState.value?.copy(isLoading = false)
                    Log.e(TAG, "Error loading dinosaurs: ${error.message}")
                }
            )
            .addTo(compositeDisposable)
    }

    fun updateFavoriteStatus(id: Int, isFavourite: Boolean) {
        encyclopediaRepository.updateFavoriteStatus(id, isFavourite)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {},
                { error ->
                    Log.e(TAG, "Error updating dinosaur status: ${error.message}")
                }
            )
            .addTo(compositeDisposable)
    }

    fun searchDinosaurs(query: String) {
        if (query.isEmpty()) {
            if (_encyclopediaUiState.value?.showFavourites == true) {
                getFavouriteDinosaurs()
            } else {
                _encyclopediaUiState.value =
                    _encyclopediaUiState.value?.copy(dinosaurs = allDinosaurs)
            }
            return
        }

        encyclopediaRepository.searchDinosaurs(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { results ->
                    if (_encyclopediaUiState.value?.showFavourites == true) {
                        _encyclopediaUiState.value =
                            _encyclopediaUiState.value?.copy(dinosaurs = results.filter { it.isFavorite })
                    } else {
                        _encyclopediaUiState.value =
                            _encyclopediaUiState.value?.copy(dinosaurs = results)
                    }
                },
                { error ->
                    Log.e(TAG, "Error searching dinosaur: ${error.message}")
                }
            )
            .addTo(compositeDisposable)
    }

    private fun getFavouriteDinosaurs() {
        encyclopediaRepository.getFavouriteDinosaurs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { favourites ->
                    _encyclopediaUiState.value =
                        _encyclopediaUiState.value?.copy(dinosaurs = favourites)
                },
                { error ->
                    Log.e(TAG, "Error fetching favourite dinosaurs: ${error.message}")
                }
            )
            .addTo(compositeDisposable)
    }

    fun toggleFavourites() {
        if (_encyclopediaUiState.value?.showFavourites == true) {
            _encyclopediaUiState.value = _encyclopediaUiState.value?.copy(
                dinosaurs = allDinosaurs,
                showFavourites = false
            )
        } else {
            _encyclopediaUiState.value =
                _encyclopediaUiState.value?.copy(showFavourites = true)
            getFavouriteDinosaurs()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        const val TAG = "EncyclopediaViewModel"
    }
}