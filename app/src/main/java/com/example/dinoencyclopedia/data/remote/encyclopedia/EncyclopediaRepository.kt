package com.example.dinoencyclopedia.data.remote.encyclopedia

import android.util.Log
import com.example.dinoencyclopedia.data.local.DinoDao
import com.example.dinoencyclopedia.data.local.DinosaurEntity
import com.example.dinoencyclopedia.data.preferences.UserPreferences
import com.example.dinoencyclopedia.data.remote.dinoapi.DinoApiService
import com.example.dinoencyclopedia.util.toEntity
import io.reactivex.rxjava3.core.Single

class EncyclopediaRepository(
    private val dinoApiService: DinoApiService,
    private val dinoDao: DinoDao,
    private val userPreferences: UserPreferences
) {
    private val cacheValidityDuration = 24 * 60 * 60 * 1000L

    fun observeAllDinosaurs() = dinoDao.getAllDinosaurs()

    fun getAllDinosaurs(forceToRefresh: Boolean): Single<List<DinosaurEntity>> {
        return if (forceToRefresh) {
            fetchFromServerAndCache()
        } else {
            isCacheValid()
                .flatMap { isValid ->
                    if (isValid) {
                        dinoDao.getAllDinosaurs().firstOrError()
                    } else {
                        fetchFromServerAndCache()
                    }
                }
        }
            .onErrorResumeNext { error ->
                Log.e("EncyclopediaRepository", "Failed to fetch from server", error)
                dinoDao.getAllDinosaurs().firstOrError()
                    .onErrorResumeNext { Single.error(error) }
            }
    }

    private fun isCacheValid(): Single<Boolean> {
        return dinoDao.getLastUpdateTime()
            .map { lastUpdate ->
                val now = System.currentTimeMillis()
                val timePassed = now - lastUpdate
                timePassed < cacheValidityDuration
            }
            .defaultIfEmpty(false)
    }

    private fun fetchFromServerAndCache(): Single<List<DinosaurEntity>> {
        return dinoApiService.getAllDinosaurs()
            .map { dinosaurs ->
                dinosaurs.map { it.toEntity() }
            }
            .flatMapCompletable { entities ->
                dinoDao.deleteAll()
                    .andThen(dinoDao.insertDinosaurs(entities))
            }
            .andThen(dinoDao.getAllDinosaurs().firstOrError())
    }

    fun updateFavoriteStatus(id: Int, isFavourite: Boolean) =
        dinoDao.updateFavoriteStatus(id, isFavourite)

    fun searchDinosaurs(query: String) = dinoDao.searchDinosaurs(query)

    fun getDinosaurById(id: Int) = dinoDao.getDinosaurById(id)

    fun getDinosaurOfTheDay(): Single<DinosaurEntity> {
        val savedId = userPreferences.dinosaurOfTheDayId

        return if (savedId == -1) {
            loadNewDinosaurOfTheDay()
        } else {
            dinoDao.getDinosaurById(savedId)
        }
    }

    fun loadNewDinosaurOfTheDay(): Single<DinosaurEntity> {
        return dinoApiService.getRandomDinosaur()
            .map { it.toEntity() }
            .doOnSuccess { dino ->
                userPreferences.dinosaurOfTheDayId = dino.id
            }
    }

    fun getFavouriteDinosaurs() = dinoDao.getFavouriteDinosaurs()
}