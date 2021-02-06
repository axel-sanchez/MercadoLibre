package com.example.mercadolibre.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mercadolibre.data.models.ResponseDTO
import com.example.mercadolibre.data.models.ResponseDTO.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface ProductRemoteSource {
    suspend fun searchProductsByName(query: String): MutableLiveData<List<Product?>>
}

/**
 * @author Axel Sanchez
 */
class ProductRemoteSourceImpl @Inject constructor(private val service: ApiService): ProductRemoteSource {
    override suspend fun searchProductsByName(query: String): MutableLiveData<List<Product?>> {
        val mutableLiveData = MutableLiveData<List<Product?>>()
        try {
            val response = service.searchProductsByName(query)
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

interface ApiService {
    @GET("search")
    suspend fun searchProductsByName(@Query("q") query: String): Response<ResponseDTO?>
}