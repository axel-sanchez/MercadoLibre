package com.example.mercadolibre.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mercadolibre.domain.DetailsUseCase

/**
 * Factory de nuestro [DetailsViewModel]
 * @author Axel Sanchez
 */
class DetailsViewModelFactory(private val detailsUseCase: DetailsUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DetailsUseCase::class.java)
            .newInstance(detailsUseCase)
    }
}