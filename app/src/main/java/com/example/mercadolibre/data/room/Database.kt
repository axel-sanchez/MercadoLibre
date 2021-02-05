package com.example.mercadolibre.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mercadolibre.data.models.ResponseDTO

/**
 * Base de datos utilizando room
 * @author Axel Sanchez
 */
@Database(
    entities = [ResponseDTO.Product::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun productDao(): ProductDao
}