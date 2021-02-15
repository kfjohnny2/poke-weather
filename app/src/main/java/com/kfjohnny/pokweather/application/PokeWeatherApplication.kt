package com.kfjohnny.pokweather.application

import android.app.Application
import androidx.multidex.MultiDex
import com.kfjohnny.pokweather.injection.modules.repositoryModules
import com.kfjohnny.pokweather.injection.modules.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PokeWeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidContext(this@PokeWeatherApplication)
            modules(listOf(repositoryModules, roomModule))
        }
    }
}