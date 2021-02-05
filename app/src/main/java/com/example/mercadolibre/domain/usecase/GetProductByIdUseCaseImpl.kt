package com.example.mercadolibre.domain.usecase

import android.util.Log
import com.example.mercadolibre.data.models.ResponseDTO
import com.example.mercadolibre.data.room.Database
import com.example.mercadolibre.domain.repository.ProductRepository
import javax.inject.Inject

interface GetProductByIdUseCase{
    suspend fun call(id: String): ResponseDTO.Product?
}

/**
 * Caso de uso para [DetailsViewModel]
 * @author Axel Sanchez
 */
class GetProductByIdUseCaseImpl @Inject constructor(private val repository: ProductRepository): GetProductByIdUseCase {

    override suspend fun call(id: String): ResponseDTO.Product? {
        return try {
            repository.getProductById(id)
        } catch (e: Exception){
            Log.e("DetailsUseCase", "Falló la petición a la base de datos local")
            null
        }
    }
}