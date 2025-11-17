package com.example.dinoencyclopedia.domain.map

import androidx.annotation.DrawableRes

data class DinoLocation(
    val id: Int,
    val title: String,
    @DrawableRes val imageRes: Int,
    val description: Int,
    val discoveryYear: Int,
    val latitude: Double,
    val longitude: Double
)
