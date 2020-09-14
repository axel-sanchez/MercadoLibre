package com.example.mercadolibre.domain

import android.util.Log
import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.data.room.Database
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception

/**
 * Caso de uso para [DetailsViewModel]
 * @author Axel Sanchez
 */
class DetailsUseCase: KoinComponent{

    private val room: Database by inject()

    /**
     * Busco en la base de datos un producto
     * @return devuelve un [Producto]
     */
    suspend fun getLocalProduct(id: String): MyResponse.Producto? {
        return try {
            room.productDao().getProduct(id)
        } catch (e: Exception){
            Log.e("DetailsUseCase", "Falló la petición a la base de datos local")
            null
        }
    }
}