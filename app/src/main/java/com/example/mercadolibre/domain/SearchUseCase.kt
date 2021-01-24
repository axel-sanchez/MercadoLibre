package com.example.mercadolibre.domain

import android.util.Log
import com.example.mercadolibre.data.models.MyResponse.Product
import com.example.mercadolibre.data.room.Database
import com.example.mercadolibre.data.room.ProductDao
import com.example.mercadolibre.data.service.ConnectToApi
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import javax.inject.Inject

/**
 * Caso de uso para [SearchViewModel]
 * @author Axel Sanchez
 */
class SearchUseCase(private val api: ConnectToApi, private val database: ProductDao) : KoinComponent {

    suspend fun getProductsBySearch(query: String): List<Product?>? {
        try {
            val localProducts = database.getProductBySearchFromLocalDataBase(query)
            if (localProducts.isNotEmpty()) return localProducts
        } catch (e: java.lang.Exception) {
            Log.e("SearchUseCase", "Falló al buscar los productos de la base de datos local vinculados con una búsqueda")
            e.printStackTrace()
        }

        val listProducts = api.searchProductsByNameFromServer(query).value

        listProducts?.let { list ->
            for (product in list) {
                product?.let {
                    try {
                        it.search = query
                        database.insertProductInLocalDataBase(it)
                    } catch (e: Exception) {
                        Log.e("SearchUseCase", "Falló al insertar un producto a la base de datos")
                        e.printStackTrace()
                    }
                }
            }
        }
        return listProducts
    }
}