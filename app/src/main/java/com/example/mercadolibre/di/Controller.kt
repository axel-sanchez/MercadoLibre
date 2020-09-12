package com.example.mercadolibre.di

import com.example.mercadolibre.data.models.service.ApiService
import com.example.mercadolibre.data.models.service.BASE_URL
import com.example.mercadolibre.data.models.service.ConnectToApi
import com.example.mercadolibre.domain.SearchUseCase
import com.example.mercadolibre.viewmodel.SearchViewModelFactory
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

}