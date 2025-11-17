package com.example.dinoencyclopedia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DinosaurEntity::class], version = 1)
abstract class DinoDatabase: RoomDatabase() {
    abstract fun getDinoDao(): DinoDao
}