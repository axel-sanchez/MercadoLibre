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
    /**
     * Api que devuelve los productos que correspondan con la búsqueda ingresada
     * @param [query] búsqueda ingresada
     * @return devuelve un objeto Response (de retrofit) que contiene [MyResponse]
     */
    @GET("search")
    suspend fun search(@Query("q") query: String): Response<MyResponse?>
}