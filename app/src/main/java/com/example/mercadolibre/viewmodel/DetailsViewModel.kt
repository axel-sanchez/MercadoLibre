package com.example.mercadolibre.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.domain.DetailsUseCase
import kotlinx.coroutines.launch

/**
 * View model de [DetailsActivity]
 * @author Axel Sanchez
 */
class DetailsViewModel @ViewModelInject constructor(private val detailsUseCase: DetailsUseCase) : ViewModel() {

    private var idProduct: String = ""
    fun setIdProduct(newId: String) {
        idProduct = newId
    }

    private val listData: MutableLiveData<MyResponse.Product?> by lazy {
        MutableLiveData<MyResponse.Product?>().also {
            getLocalProduct(idProduct)
        }
    }

    private fun setListData(product: MyResponse.Product?) {
        listData.postValue(product)
    }

    private fun getLocalProduct(id: String) {
        viewModelScope.launch {
            setListData(detailsUseCase.getProductByIdFromLocalDataBase(id))
        }
    }

    fun getLocalProductLiveData(): LiveData<MyResponse.Product?> {
        return listData
    }
}