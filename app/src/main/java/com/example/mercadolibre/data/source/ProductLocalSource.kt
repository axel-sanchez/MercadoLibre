package com.example.mercadolibre.data.source

import com.example.mercadolibre.data.models.ResponseDTO.*
import com.example.mercadolibre.data.room.ProductDao

interface ProductLocalSource{
    suspend fun getProductBySearch(search: String): List<Product?>
    suspend fun getProductById(id: String): Product?
    suspend fun insertProduct(product: Product?): Long
}

/**
 * @author Axel Sanchez
 */
class ProductLocalSourceImpl(private val database: ProductDao): ProductLocalSource{
    override suspend fun getProductBySearch(search: String): List<Product?> = database.getProductBySearch(search)
    override suspend fun getProductById(id: String): Product? = database.getProductById(id)
    override suspend fun insertProduct(product: Product?): Long = database.insertProduct(product)
}