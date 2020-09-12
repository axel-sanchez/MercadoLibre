package com.example.mercadolibre.domain

import com.example.mercadolibre.data.models.search.Result
import com.example.mercadolibre.data.models.service.ConnectToApi
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Caso de uso para [ViewModelSearch]
 * @author Axel Sanchez
 */
class SearchUseCase : KoinComponent {
    private val api: ConnectToApi by inject()
    //private val repository: GenericRepository by inject()

    /**
     *
     * @return devuelve un any
     */
    suspend fun getSearch(query: String): List<Result?>? {
        return api.getSearch(query).value
    }
}