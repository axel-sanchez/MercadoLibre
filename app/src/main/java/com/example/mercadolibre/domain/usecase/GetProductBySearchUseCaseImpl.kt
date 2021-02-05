package com.example.mercadolibre.domain.usecase

import android.util.Log
import com.example.mercadolibre.data.models.ResponseDTO.Product
import com.example.mercadolibre.data.room.ProductDao
import com.example.mercadolibre.data.source.ProductRemoteSource
import com.example.mercadolibre.domain.repository.ProductRepository
import javax.inject.Inject

interface GetProductBySearchUseCase{
    suspend fun call(query: String): List<Product?>?
}

/**
 * Caso de uso para [SearchViewModel]
 * @author Axel Sanchez
 */
class GetProductBySearchUseCaseImpl @Inject constructor(private val repository: ProductRepository): GetProductBySearchUseCase {

    override suspend fun call(query: String): List<Product?>? {
        return repository.getProductBySearch(query)
    }
}