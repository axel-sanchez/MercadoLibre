package com.example.mercadolibre.data.room

import androidx.room.*
import com.example.mercadolibre.data.models.MyResponse.Product

/**
 * Dao utilizado para la implementación de la base de datos con room
 * @author Axel Sanchez
 */
@Dao
interface ProductDao {

    @Query("SELECT * FROM Product WHERE search = :search")
    suspend fun getProductBySearchFromLocalDataBase(search: String): List<Product?>

    @Query("SELECT * FROM Product WHERE ID = :id")
    suspend fun getProductByIdFromLocalDataBase(id: String): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductInLocalDataBase(product: Product?)
}