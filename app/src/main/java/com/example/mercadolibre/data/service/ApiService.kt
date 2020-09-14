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
     * Api que devuelve las peliculas más populares
     * @param [apiKey] código que nos da la página de la api
     * @return devuelve un [Producto]
     */
    @GET("search")
    suspend fun search(@Query("q") query: String): Response<MyResponse?>
}