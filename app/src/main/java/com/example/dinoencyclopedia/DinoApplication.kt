package com.example.dinoencyclopedia

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.dinoencyclopedia.di.localDataModule
import com.example.dinoencyclopedia.di.networkModule
import com.example.dinoencyclopedia.di.repositoryModule
import com.example.dinoencyclopedia.di.viewModelModule
import com.example.dinoencyclopedia.workmanager.DinosaurOfTheDayWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.concurrent.TimeUnit

class DinoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DinoApplication)
            androidLogger(Level.DEBUG)
            modules(localDataModule, networkModule, repositoryModule, viewModelModule)
        }
        initWorker()
    }

    private fun initWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<DinosaurOfTheDayWorker>(
            24, TimeUnit.HOURS,
            1, TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "dinosaur_of_the_day_work",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }
}

