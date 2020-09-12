package com.example.mercadolibre.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mercadolibre.domain.SearchUseCase

/**
 * Factory de nuestro [SearchViewModel]
 * @author Axel Sanchez
 */
class SearchViewModelFactory(private val searchUseCase: SearchUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SearchUseCase::class.java).newInstance(searchUseCase)
    }
}