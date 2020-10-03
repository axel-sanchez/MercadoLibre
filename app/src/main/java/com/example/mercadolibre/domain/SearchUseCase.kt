package com.example.mercadolibre.domain

import android.util.Log
import com.example.mercadolibre.data.models.MyResponse.Product
import com.example.mercadolibre.data.room.Database
import com.example.mercadolibre.data.service.ConnectToApi
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Caso de uso para [SearchViewModel]
 * @author Axel Sanchez
 */
class SearchUseCase : KoinComponent {
    private val api: ConnectToApi by inject()
    private val room: Database by inject()

    /**
     * Hago una busqueda localmente y si no hay productos llamo a la api
     * @return devuelve un listado de [Product]
     */
    suspend fun getSearch(query: String): List<Product?>? {
        try {
            val localProducts = room.productDao().getProductFromSearch(query)
            if (localProducts.isNotEmpty()) return localProducts
        } catch (e: java.lang.Exception) {
            Log.e("SearchUseCase", "Falló al buscar los productos de la base de datos local vinculados con una búsqueda")
            e.printStackTrace()
        }

        val listProductos = api.getSearch(query).value

        listProductos?.let { listado ->
            for (producto in listado) {
                producto?.let {
                    try {
                        producto.search = query
                        room.productDao()
                            .insert(it)
                    } catch (e: Exception) {
                        Log.e("SearchUseCase", "Falló al insertar un producto a la base de datos")
                        e.printStackTrace()
                    }
                }
            }
        }
        return listProductos
    }
}