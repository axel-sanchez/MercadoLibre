package com.example.mercadolibre.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application que inicializa la inyección de dependencias de Koin
 * @author Axel Sanchez
 */
@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}