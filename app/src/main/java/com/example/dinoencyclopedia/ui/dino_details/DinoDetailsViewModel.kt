package com.example.dinoencyclopedia.ui.dino_details

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

class DinoDetailsViewModel(
    private val encyclopediaRepository: EncyclopediaRepository
) : ViewModel() {

    private val _dinosaur = MutableLiveData<DinosaurEntity>()
    val dinosaur: LiveData<DinosaurEntity> = _dinosaur

    private val compositeDisposable = CompositeDisposable()

    fun loadDinosaur(id: Int) {
        encyclopediaRepository.getDinosaurById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dinosaur ->
                    _dinosaur.value = dinosaur
                },
                { error ->
                    Log.e(TAG, "Error loading dino: ${error.message}")
                }
            )
            .addTo(compositeDisposable)
    }

    companion object {
        const val TAG = "DinoDetailsVM"
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}