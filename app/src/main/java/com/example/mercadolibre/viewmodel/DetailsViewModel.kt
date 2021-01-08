package com.example.mercadolibre.viewmodel

import androidx.lifecycle.*
import com.example.mercadolibre.data.models.MyResponse
import com.example.mercadolibre.domain.DetailsUseCase
import kotlinx.coroutines.launch

/**
 * View model de [DetailsActivity]
 * @author Axel Sanchez
 */
class DetailsViewModel(private val detailsUseCase: DetailsUseCase) : ViewModel() {

    private val listData = MutableLiveData<MyResponse.Product?>()

    private fun setListData(product: MyResponse.Product?) {
        listData.postValue(product)
    }

    fun getLocalProduct(id: String) {
        viewModelScope.launch {
            setListData(detailsUseCase.getProductByIdFromLocalDataBase(id))
        }
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