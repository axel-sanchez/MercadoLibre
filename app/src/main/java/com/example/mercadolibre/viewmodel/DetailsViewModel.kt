package com.example.mercadolibre.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mercadolibre.data.models.ResponseDTO
import com.example.mercadolibre.domain.usecase.GetProductByIdUseCase
import kotlinx.coroutines.launch

/**
 * View model de [DetailsActivity]
 * @author Axel Sanchez
 */
class DetailsViewModel @ViewModelInject constructor(private val getProductByIdUseCase: GetProductByIdUseCase) : ViewModel() {

    private var idProduct: String = ""
    fun setIdProduct(newId: String) {
        idProduct = newId
    }

    private val listData: MutableLiveData<ResponseDTO.Product?> by lazy {
        MutableLiveData<ResponseDTO.Product?>().also {
            getLocalProduct(idProduct)
        }
    }

    private fun setListData(product: ResponseDTO.Product?) {
        listData.postValue(product)
    }

    private fun getLocalProduct(id: String) {
        viewModelScope.launch {
            setListData(getProductByIdUseCase.call(id))
        }
    }

    fun getLocalProductLiveData(): LiveData<ResponseDTO.Product?> {
        return listData
    }
}