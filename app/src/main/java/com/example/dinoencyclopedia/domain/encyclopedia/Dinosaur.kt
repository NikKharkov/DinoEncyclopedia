package com.example.dinoencyclopedia.domain.encyclopedia

import com.google.gson.annotations.SerializedName

data class Dinosaur(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("short_description")
    val shortDescription: String,

    @SerializedName("long_description")
    val longDescription: String,

    @SerializedName("period")
    val period: String,

    @SerializedName("length")
    val length: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("diet")
    val diet: String,

    @SerializedName("weight")
    val weight: String,

    @SerializedName("image_url")
    val imageUrl: String
)
