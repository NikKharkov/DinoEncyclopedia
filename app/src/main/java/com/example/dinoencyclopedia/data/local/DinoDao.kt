package com.example.dinoencyclopedia.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface DinoDao {

    @Query("SELECT * FROM dinosaurs")
    fun getAllDinosaurs(): Flowable<List<DinosaurEntity>>

    @Query("SELECT * FROM dinosaurs WHERE isFavorite = 1")
    fun getFavouriteDinosaurs(): Flowable<List<DinosaurEntity>>

    @Query("SELECT * FROM dinosaurs WHERE id = :id")
    fun getDinosaurById(id: Int): Single<DinosaurEntity>

    @Query("SELECT * FROM dinosaurs WHERE name LIKE '%' || :query || '%'")
    fun searchDinosaurs(query: String): Flowable<List<DinosaurEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDinosaurs(dinosaurs: List<DinosaurEntity>): Completable

    @Query("UPDATE dinosaurs SET isFavorite = :isFavourite WHERE id = :id ")
    fun updateFavoriteStatus(id: Int, isFavourite: Boolean): Completable
    @Query("DELETE FROM dinosaurs")
    fun deleteAll(): Completable

    @Query("SELECT timestamp FROM dinosaurs LIMIT 1")
    fun getLastUpdateTime(): Maybe<Long>
}