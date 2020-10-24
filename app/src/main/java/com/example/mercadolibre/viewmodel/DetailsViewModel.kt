package com.example.mercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.domain.DetailsUseCase

/**
 * View model de [DetailsActivity]
 * @author Axel Sanchez
 */
class DetailsViewModel(private val detailsUseCase: DetailsUseCase) : ViewModel() {

    private val listData = MutableLiveData<MyResponse.Product?>()

    private fun setListData(product: MyResponse.Product?) {
        listData.postValue(product)
    }

    suspend fun getLocalProduct(id: String) {
        setListData(detailsUseCase.getLocalProduct(id))
    }

    fun getLocalProductLiveData(): LiveData<MyResponse.Product?> {
        return listData
    }

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
}