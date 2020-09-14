package com.example.mercadolibre.di

import androidx.room.Room
import com.example.mercadolibre.data.room.Database
import com.example.mercadolibre.data.service.ApiService
import com.example.mercadolibre.data.service.BASE_URL
import com.example.mercadolibre.data.service.ConnectToApi
import com.example.mercadolibre.domain.DetailsUseCase
import com.example.mercadolibre.domain.SearchUseCase
import com.example.mercadolibre.viewmodel.DetailsViewModelFactory
import com.example.mercadolibre.viewmodel.SearchViewModelFactory
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Axel Sanchez
 */
val moduleApp = module{
    single{ Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build() }
    single { (get() as Retrofit).create(ApiService::class.java) }
    single { SearchUseCase() }
    single { SearchViewModelFactory(get()) }
    single { ConnectToApi() }
    single { Room
        .databaseBuilder(androidContext(), Database::class.java, "mercadoLibreDB.db3")
        .build() }
    single { DetailsUseCase() }
    single { DetailsViewModelFactory(get()) }
    single { Gson() }
}