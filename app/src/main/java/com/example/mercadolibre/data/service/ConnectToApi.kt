package com.example.mercadolibre.data.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception
import com.example.mercadolibre.data.models.MyResponse.Producto

const val BASE_URL = "https://api.mercadolibre.com/sites/MLA/"

/**
 * Esta clase es la encargada de conectarse a la api
 * @author Axel Sanchez
 */
class ConnectToApi : KoinComponent {
    private val service: ApiService by inject()

    /**
     * Esta función es la encargada de retornar la búsqueda de productos
     * @param [query] es la búsqueda
     * @return devuelve un mutableLiveData que contiene un listado de [Producto]
     */
    suspend fun getSearch(query: String): MutableLiveData<List<Producto?>> {
        var mutableLiveData = MutableLiveData<List<Producto?>>()
        try {
            var response = service.search(query)
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body()
                    ?.let { it.toString() } ?: "")
                mutableLiveData.value = response.body()
                    ?.let { it.results } ?: listOf()
            } else {
                Log.i("Error Response", response.errorBody().toString())
                mutableLiveData.value = listOf()
            }
        } catch (e: Exception) {
            mutableLiveData.value = listOf()
            Log.e("ConnectToApi", "Error al obtener los productos y guardarlos en el livedata")
            Log.e("Query", query)
            e.printStackTrace()
        }
        return mutableLiveData
    }
}