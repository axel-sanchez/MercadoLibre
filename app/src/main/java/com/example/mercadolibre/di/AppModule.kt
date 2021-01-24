package com.example.mercadolibre.di

import android.content.Context
import androidx.room.Room
import com.example.mercadolibre.data.room.Database
import com.example.mercadolibre.data.room.ProductDao
import com.example.mercadolibre.data.service.ApiService
import com.example.mercadolibre.data.service.ConnectToApi
import com.example.mercadolibre.domain.DetailsUseCase
import com.example.mercadolibre.domain.SearchUseCase
import com.example.mercadolibre.helpers.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context.applicationContext, Database::class.java, "mercadoLibreDB.db3").build()

    @Provides
    @Singleton
    fun getRoomDao(database: Database) = database.productDao()

    @Provides
    @Singleton
    fun getSearchUseCase(api: ConnectToApi, productDao: ProductDao) = SearchUseCase(api, productDao)

    @Provides
    @Singleton
    fun getApi(apiService: ApiService) = ConnectToApi(apiService)

    @Provides
    @Singleton
    fun getDetailsUseCase(room: Database) = DetailsUseCase(room)

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun getService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
