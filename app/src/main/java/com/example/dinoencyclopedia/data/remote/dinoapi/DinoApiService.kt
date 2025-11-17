package com.example.dinoencyclopedia.data.remote.dinoapi

import com.example.dinoencyclopedia.domain.encyclopedia.Dinosaur
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DinoApiService {

    @GET("/dinosaurs")
    fun getAllDinosaurs(): Single<List<Dinosaur>>

    @GET("/dinosaurs/random")
    fun getRandomDinosaur(): Single<Dinosaur>
}