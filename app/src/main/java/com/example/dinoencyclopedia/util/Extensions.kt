package com.example.dinoencyclopedia.util

import com.example.dinoencyclopedia.data.local.DinosaurEntity
import com.example.dinoencyclopedia.domain.encyclopedia.Dinosaur


fun Dinosaur.toEntity() = DinosaurEntity(
    id = id,
    name = name,
    shortDescription = shortDescription,
    longDescription = longDescription,
    period = period,
    length = length,
    location = location,
    diet = diet,
    weight = weight,
    imageUrl = imageUrl,
    timestamp = System.currentTimeMillis()
)

fun DinosaurEntity.toDomain() = Dinosaur(
    id = id,
    name = name,
    shortDescription = shortDescription,
    longDescription = longDescription,
    period = period,
    length = length,
    location = location,
    diet = diet,
    weight = weight,
    imageUrl = imageUrl
)