package com.example.dinoencyclopedia.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dinoencyclopedia.data.local.DinoDatabase
import com.example.dinoencyclopedia.data.preferences.UserPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val localDataModule = module {
    singleOf(::UserPreferences)

    single {
        Room.databaseBuilder(
            androidContext(),
            DinoDatabase::class.java,
            "dino_db"
        ).build()
    }

    single { get<DinoDatabase>().getDinoDao() }
}