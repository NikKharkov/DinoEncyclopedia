package com.example.dinoencyclopedia.domain.news

import androidx.annotation.DrawableRes
import com.example.dinoencyclopedia.R

enum class NewsCategory(
    val displayName: String,
    val keywords: List<String>,
    @DrawableRes val placeholderRes: Int
) {
    ALL(
        displayName = "All",
        keywords = emptyList(),
        placeholderRes = R.drawable.placeholder_paleontology
    ),
    DINOSAURS(
        displayName = "Dinosaurs",
        keywords = listOf(
            "dinosaur",
            "t-rex",
            "triceratops",
            "sauropod",
            "velociraptor",
            "pterosaur"
        ),
        placeholderRes = R.drawable.placeholder_dinosaur
    ),
    FOSSILS(
        displayName = "Fossils",
        keywords = listOf("fossil", "bone", "skeleton", "remains"),
        placeholderRes = R.drawable.placeholder_fossil
    ),
    EGGS(
        displayName = "Eggs",
        keywords = listOf("egg", "hatch", "nest", "embryo"),
        placeholderRes = R.drawable.placeholder_egg
    ),
    HUMANS(
        displayName = "Human Evolution",
        keywords = listOf("human", "neanderthal", "homo sapiens", "ancestor", "hominid"),
        placeholderRes = R.drawable.placeholder_human
    ),
    MARINE(
        displayName = "Marine Life",
        keywords = listOf("marine", "ichthyosaur", "plesiosaur", "ocean", "mosasaur", "sea"),
        placeholderRes = R.drawable.placeholder_marine
    ),
    ICE_AGE(
        displayName = "Ice Age",
        keywords = listOf("mammoth", "ice age", "pleistocene", "saber", "megafauna"),
        placeholderRes = R.drawable.placeholder_mammoth
    ),
    DISCOVERIES(
        displayName = "Discoveries",
        keywords = listOf("excavat", "discover", "unearth", "found", "new species"),
        placeholderRes = R.drawable.placeholder_excavation
    );

    fun detectNewsCategory(text: String): NewsCategory {
        val lowercaseText = text.lowercase()

        return entries
            .filter { it != ALL }
            .firstOrNull { category ->
                category.keywords.any { keyword -> lowercaseText.contains(keyword) }
            } ?: ALL
    }
}