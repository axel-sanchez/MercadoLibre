package com.example.mercadolibre.data.room

import androidx.room.*
import com.example.mercadolibre.data.models.MyResponse.Producto

/**
 * @author Axel Sanchez
 */
@Dao
interface ProductoDao {

    @Query("SELECT * FROM Producto WHERE search = :search")
    suspend fun getProductFromSearch(search: String): List<Producto?>

    @Query("SELECT * FROM Producto WHERE ID = :id")
    suspend fun getProduct(id: String): Producto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: Producto?)

    @Delete
    suspend fun delete(Producto: Producto?)
}