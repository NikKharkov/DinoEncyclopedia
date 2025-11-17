package com.example.dinoencyclopedia.workmanager

import android.content.Context
import androidx.work.WorkerParameters
import androidx.work.rxjava3.RxWorker
import com.example.dinoencyclopedia.data.remote.encyclopedia.EncyclopediaRepository
import io.reactivex.rxjava3.core.Single
import org.koin.java.KoinJavaComponent.inject

class DinosaurOfTheDayWorker(
    context: Context,
    params: WorkerParameters
) : RxWorker(context, params) {

    private val encyclopediaRepository: EncyclopediaRepository by inject(EncyclopediaRepository::class.java)

    override fun createWork(): Single<Result> {
        return encyclopediaRepository.loadNewDinosaurOfTheDay()
            .map { Result.success() }
            .onErrorReturn {
                Result.retry()
            }
    }
}