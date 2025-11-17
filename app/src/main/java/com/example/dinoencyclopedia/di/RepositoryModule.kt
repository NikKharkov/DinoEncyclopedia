package com.example.dinoencyclopedia.di

import com.example.dinoencyclopedia.data.remote.encyclopedia.EncyclopediaRepository
import com.example.dinoencyclopedia.data.remote.news.NewsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::NewsRepository)
    singleOf(::EncyclopediaRepository)
}