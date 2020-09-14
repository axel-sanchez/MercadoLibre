package com.example.mercadolibre.domain

import com.example.mercadolibre.data.models.MyResponse.Producto
import com.example.mercadolibre.data.room.Database
import com.example.mercadolibre.data.service.ConnectToApi
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Caso de uso para [ViewModelSearch]
 * @author Axel Sanchez
 */
class SearchUseCase : KoinComponent {
    private val api: ConnectToApi by inject()
    private val room: Database by inject()

    /**
     * @return devuelve un any
     */
    suspend fun getSearch(query: String): List<Producto?>? {
        var localProducts = room.productDao().getProductFromSearch(query)
        if(localProducts.isNotEmpty()) return localProducts

        var listProductos = api.getSearch(query).value

        listProductos?.let { listado ->
            for (producto in listado){
                producto?.let {
                    producto.search = query
                    try {
                        room.productDao().insert(it)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }
        }

        return listProductos
    }
}