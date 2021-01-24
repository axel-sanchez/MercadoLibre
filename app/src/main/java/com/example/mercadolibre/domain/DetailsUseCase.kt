package com.example.mercadolibre.domain

import android.util.Log
import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.data.room.Database
import javax.inject.Inject

/**
 * Caso de uso para [DetailsViewModel]
 * @author Axel Sanchez
 */
class DetailsUseCase @Inject constructor(private val room: Database){

    suspend fun getProductByIdFromLocalDataBase(id: String): MyResponse.Product? {
        return try {
            room.productDao().getProductByIdFromLocalDataBase(id)
        } catch (e: Exception){
            Log.e("DetailsUseCase", "Falló la petición a la base de datos local")
            null
        }
    }
}