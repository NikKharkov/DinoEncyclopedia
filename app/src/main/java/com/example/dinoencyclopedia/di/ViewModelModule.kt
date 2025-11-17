package com.example.dinoencyclopedia.di

import com.example.dinoencyclopedia.ui.dino_details.DinoDetailsViewModel
import com.example.dinoencyclopedia.ui.encyclopedia.EncyclopediaViewModel
import com.example.dinoencyclopedia.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::EncyclopediaViewModel)
    viewModelOf(::DinoDetailsViewModel)
}