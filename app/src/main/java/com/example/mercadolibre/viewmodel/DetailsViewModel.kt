package com.example.mercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.domain.DetailsUseCase

/**
 * View model de [DetailsFragment]
 * @author Axel Sanchez
 */
class DetailsViewModel(private val detailsUseCase: DetailsUseCase) : ViewModel() {

    private val listData = MutableLiveData<MyResponse.Producto?>()

    private fun setListData(product: MyResponse.Producto?) {
        listData.postValue(product)
    }

    suspend fun getLocalProduct(id: String) {
        setListData(detailsUseCase.getLocalProduct(id))
    }

    fun getLocalProductLiveData(): LiveData<MyResponse.Producto?> {
        return listData
    }
}