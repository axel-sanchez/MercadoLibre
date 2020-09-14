package com.example.mercadolibre.aplication

import android.app.Application
import com.example.mercadolibre.di.moduleApp
import org.koin.android.ext.android.startKoin

/**
 * Application que inicializa la inyección de dependencias de Koin
 * @author Axel Sanchez
 */
class MyAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, listOf(moduleApp))
    }
}