package com.example.mercadolibre.data.service

import com.example.mercadolibre.data.models.MyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface para generar las peticiones a la api
 * @author Axel Sanchez
 */
interface ApiService {
    @GET("search")
    suspend fun searchProductsByNameFromServer(@Query("q") query: String): Response<MyResponse?>
}