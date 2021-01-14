package com.example.mercadolibre.data.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception
import com.example.mercadolibre.data.models.MyResponse.Product

/**
 * Esta clase es la encargada de conectarse a la api
 * @author Axel Sanchez
 */
class ConnectToApi : KoinComponent {
    private val service: ApiService by inject()

    suspend fun searchProductsByNameFromServer(query: String): MutableLiveData<List<Product?>> {
        val mutableLiveData = MutableLiveData<List<Product?>>()
        try {
            val response = service.searchProductsByNameFromServer(query)
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body()?.let { it.toString() } ?: "")
                mutableLiveData.value = response.body()?.let { it.results } ?: listOf()
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