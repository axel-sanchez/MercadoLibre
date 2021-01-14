package com.example.mercadolibre.viewmodel

import androidx.lifecycle.*
import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.domain.DetailsUseCase
import kotlinx.coroutines.launch

/**
 * View model de [DetailsActivity]
 * @author Axel Sanchez
 */
class DetailsViewModel(private val detailsUseCase: DetailsUseCase, private val idProduct: String) : ViewModel() {

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

    class DetailsViewModelFactory(private val detailsUseCase: DetailsUseCase, private val idProduct: String) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(DetailsUseCase::class.java, String::class.java)
                .newInstance(detailsUseCase, idProduct)
        }
    }
}