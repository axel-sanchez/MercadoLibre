package com.example.mercadolibre.data.models.service

import com.example.mercadolibre.data.models.search.ResponseSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface para generar las peticiones a la api
 * @author Axel Sanchez
 */
interface ApiService {
    /**
     * Api que devuelve las peliculas más populares
     * @param [apiKey] código que nos da la página de la api
     * @return devuelve un [Result]
     */
    @GET("search")
    suspend fun search(@Query("q") query: String): Response<ResponseSearch?>
}