package com.example.mercadolibre.domain

import android.util.Log
import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.data.room.Database
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception
import javax.inject.Inject

/**
 * Caso de uso para [DetailsViewModel]
 * @author Axel Sanchez
 */
class DetailsUseCase(private val room: Database): KoinComponent{

    suspend fun getProductByIdFromLocalDataBase(id: String): MyResponse.Product? {
        return try {
            room.productDao().getProductByIdFromLocalDataBase(id)
        } catch (e: Exception){
            Log.e("DetailsUseCase", "Falló la petición a la base de datos local")
            null
        }
    }
}