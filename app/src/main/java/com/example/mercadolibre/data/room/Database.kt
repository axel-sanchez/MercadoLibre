package com.example.mercadolibre.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mercadolibre.data.models.MyResponse

/**
 * Base de datos utilizando room
 * @author Axel Sanchez
 */
@Database(
    entities = [MyResponse.Product::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun productDao(): ProductDao
}