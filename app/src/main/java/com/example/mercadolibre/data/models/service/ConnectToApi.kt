package com.example.mercadolibre.data.models.service

import androidx.lifecycle.MutableLiveData
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception
import com.example.mercadolibre.data.models.search.Result

const val BASE_URL = "https://api.mercadolibre.com/sites/MLA/"

/**
 * Esta clase es la encargada de conectarse a las api's
 * @author Axel Sanchez
 */
class ConnectToApi : KoinComponent {
    private val service: ApiService by inject()

    /**
     * Esta función es la encargada de retornar las movies mas populares
     * @return devuelve un mutableLiveData que contiene un listado de [Movie] populares
     */
    suspend fun getSearch(query: String): MutableLiveData<List<Result?>> {
        var mutableLiveData = MutableLiveData<List<Result?>>()
        try {
            var response = service.search(query)
            if (response.isSuccessful) mutableLiveData.value = response.body()?.let { it.results } ?: listOf()
            else mutableLiveData.value = listOf()
        } catch (e:Exception){
            e.printStackTrace()
        }
        println("cantidad del listado: ${mutableLiveData.value?.size}")
        return mutableLiveData
    }
}