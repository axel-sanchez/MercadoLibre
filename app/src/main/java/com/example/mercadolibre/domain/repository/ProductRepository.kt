package com.example.mercadolibre.domain.repository

import com.example.mercadolibre.data.models.ResponseDTO.*

/**
 * @author Axel Sanchez
 */
interface ProductRepository {
    suspend fun getProductById(id: String): Product?
    suspend fun getProductBySearch(query: String): List<Product?>
}