package com.example.mercadolibre.di

import android.content.Context
import androidx.room.Room
import com.example.mercadolibre.data.repository.ProductRepositoryImpl
import com.example.mercadolibre.data.room.Database
import com.example.mercadolibre.data.room.ProductDao
import com.example.mercadolibre.data.source.*
import com.example.mercadolibre.domain.repository.ProductRepository
import com.example.mercadolibre.domain.usecase.GetProductByIdUseCase
import com.example.mercadolibre.domain.usecase.GetProductByIdUseCaseImpl
import com.example.mercadolibre.domain.usecase.GetProductBySearchUseCase
import com.example.mercadolibre.domain.usecase.GetProductBySearchUseCaseImpl
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
    fun getSearchUseCase(repository: ProductRepository): GetProductBySearchUseCase = GetProductBySearchUseCaseImpl(repository)

    @Provides
    @Singleton
    fun getProductRemoteSource(apiService: ApiService): ProductRemoteSource = ProductRemoteSourceImpl(apiService)

    @Provides
    @Singleton
    fun getProductLocalSource(database: ProductDao): ProductLocalSource = ProductLocalSourceImpl(database)

    @Provides
    @Singleton
    fun getDetailsUseCase(repository: ProductRepository): GetProductByIdUseCase = GetProductByIdUseCaseImpl(repository)

    @Provides
    @Singleton
    fun getProductRepository(productRemoteSource: ProductRemoteSource, productLocalSource: ProductLocalSource): ProductRepository = ProductRepositoryImpl(productRemoteSource, productLocalSource)

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