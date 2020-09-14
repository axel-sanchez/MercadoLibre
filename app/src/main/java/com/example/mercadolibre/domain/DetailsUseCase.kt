package com.example.mercadolibre.domain

import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.data.room.Database
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception

/**
 * Caso de uso para
 * @author Axel Sanchez
 */
class DetailsUseCase: KoinComponent{

    private val room: Database by inject()

    /**
     *
     * @return devuelve un any
     */
    suspend fun getLocalProduct(id: String): MyResponse.Producto? {
        try {
            var producto = room.productDao().getProduct(id)
            println(producto)
            return producto
        } catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }
}