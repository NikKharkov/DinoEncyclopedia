package com.example.dinoencyclopedia.ui.encyclopedia

import com.example.dinoencyclopedia.data.local.DinosaurEntity

data class EncyclopediaUiState(
    val isLoading: Boolean = false,
    val showFavourites: Boolean = false,
    val dinosaurs: List<DinosaurEntity> = emptyList()
)
