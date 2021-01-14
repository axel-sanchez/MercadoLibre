package com.example.mercadolibre.viewmodel

import androidx.lifecycle.*
import com.example.mercadolibre.data.models.MyResponse.Product
import com.example.mercadolibre.domain.SearchUseCase
import kotlinx.coroutines.launch

/**
 * View model de [SearchFragment]
 * @author Axel Sanchez
 */
class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val listData = MutableLiveData<List<Product?>?>()

    private fun setListData(listProduct: List<Product?>?) {
        listData.value = listProduct
    }

    fun getSearch(query: String) {
        viewModelScope.launch {
            setListData(searchUseCase.getProductsBySearch(query))
        }
    }

    fun getSearchLiveData(): LiveData<List<Product?>?> {
        return listData
    }

    class SearchViewModelFactory(private val searchUseCase: SearchUseCase) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(SearchUseCase::class.java).newInstance(searchUseCase)
        }
    }
}