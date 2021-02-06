package com.example.mercadolibre.data.repository

import android.util.Log
import com.example.mercadolibre.data.models.ResponseDTO.*
import com.example.mercadolibre.data.source.ProductLocalSource
import com.example.mercadolibre.data.source.ProductRemoteSource
import com.example.mercadolibre.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * @author Axel Sanchez
 */
class ProductRepositoryImpl @Inject constructor(private val api: ProductRemoteSource, private val productLocalSource: ProductLocalSource): ProductRepository {
    override suspend fun getProductById(id: String): Product? {
        return productLocalSource.getProductById(id)
    }

    override suspend fun getProductBySearch(query: String): List<Product?> {
        try {
            val localProducts = productLocalSource.getProductBySearch(query)
            if (localProducts.isNotEmpty()) return localProducts
        } catch (e: java.lang.Exception) {
            Log.e("SearchUseCase", "Falló al buscar los productos de la base de datos local vinculados con una búsqueda")
            e.printStackTrace()
        }

        val listProducts = api.searchProductsByName(query).value

        listProducts?.let { list ->
            for (product in list) {
                product?.let {
                    try {
                        it.search = query
                        productLocalSource.insertProduct(it)
                    } catch (e: Exception) {
                        Log.e("SearchUseCase", "Falló al insertar un producto a la base de datos")
                        e.printStackTrace()
                    }
                }
            }
        }
        return listProducts?: listOf()
    }
}