package com.example.mercadolibre.data.source

import com.example.mercadolibre.data.models.ResponseDTO
import com.example.mercadolibre.data.room.ProductDao

/**
 * @author Axel Sanchez
 */
interface ProductLocalSource{
    suspend fun getProductBySearch(search: String): List<ResponseDTO.Product?>

    suspend fun getProductById(id: String): ResponseDTO.Product?

    suspend fun insertProduct(product: ResponseDTO.Product?): Long
}

class ProductLocalSourceImpl(private val database: ProductDao): ProductLocalSource{

    override suspend fun getProductBySearch(search: String): List<ResponseDTO.Product?> = database.getProductBySearch(search)

    override suspend fun getProductById(id: String): ResponseDTO.Product? = database.getProductById(id)

    override suspend fun insertProduct(product: ResponseDTO.Product?): Long = database.insertProduct(product)
}