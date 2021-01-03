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

    private fun setListData(listProducto: List<Product?>?) {
        listData.value = listProducto
    }

    fun getSearch(query: String) {
        viewModelScope.launch {
            setListData(searchUseCase.getSearch(query))
        }
    }

    fun getSearchLiveData(): LiveData<List<Product?>?> {
        return listData
    }

    /**
     * Factory de nuestro [SearchViewModel]
     * @author Axel Sanchez
     */
    class SearchViewModelFactory(private val searchUseCase: SearchUseCase) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(SearchUseCase::class.java).newInstance(searchUseCase)
        }
    }
}