package com.example.mercadolibre.data.room

import androidx.room.*
import com.example.mercadolibre.data.models.ResponseDTO.Product

/**
 * Dao utilizado para la implementación de la base de datos con room
 * @author Axel Sanchez
 */
@Dao
interface ProductDao {

    @Query("SELECT * FROM Product WHERE search = :search")
    suspend fun getProductBySearch(search: String): List<Product?>

    @Query("SELECT * FROM Product WHERE ID = :id")
    suspend fun getProductById(id: String): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product?): Long
}