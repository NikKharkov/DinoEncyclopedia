package com.example.dinoencyclopedia.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dinosaurs")
data class DinosaurEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val period: String,
    val length: String,
    val location: String,
    val diet: String,
    val weight: String,
    val imageUrl: String,
    val isFavorite: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
