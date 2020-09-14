package com.example.mercadolibre.data.room

import androidx.room.*
import com.example.mercadolibre.data.models.MyResponse.Producto

/**
 * Dao utilizado para la implementación de la base de datos con room
 * @author Axel Sanchez
 */
@Dao
interface ProductoDao {

    /**
     * Obtengo los productos de una búsqueda determinada desde la base de datos local
     * @param [search] es búsqueda que se intenta obtener
     * @return listado de productos
     * */
    @Query("SELECT * FROM Producto WHERE search = :search")
    suspend fun getProductFromSearch(search: String): List<Producto?>

    /**
     * Obtengo un producto de id determinado de la base de datos local
     * @param [id] id del producto
     * @return un producto
     * */
    @Query("SELECT * FROM Producto WHERE ID = :id")
    suspend fun getProduct(id: String): Producto?

    /**
     * Inserto un producto en la base de datos local
     * @param [producto] que se intenta insertar
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: Producto?)
}