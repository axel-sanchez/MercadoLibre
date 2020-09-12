package com.example.mercadolibre

import android.app.Application
import com.example.mercadolibre.di.moduleApp
import org.koin.android.ext.android.startKoin

/**
 * @author Axel Sanchez
 */
class MyAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, listOf(moduleApp))
    }
}