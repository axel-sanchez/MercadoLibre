package com.example.mercadolibre.domain.usecase

import com.example.mercadolibre.data.models.ResponseDTO.Product
import com.example.mercadolibre.domain.repository.ProductRepository
import javax.inject.Inject

interface GetProductBySearchUseCase{
    suspend fun call(query: String): List<Product?>?
}

/**
 * @author Axel Sanchez
 */
class GetProductBySearchUseCaseImpl @Inject constructor(private val repository: ProductRepository): GetProductBySearchUseCase {

    override suspend fun call(query: String): List<Product?> {
        return repository.getProductBySearch(query)
    }
}