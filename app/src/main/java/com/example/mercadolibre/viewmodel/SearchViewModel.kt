package com.example.mercadolibre.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mercadolibre.data.models.MyResponse.Product
import com.example.mercadolibre.domain.SearchUseCase
import kotlinx.coroutines.launch

/**
 * View model de [SearchFragment]
 * @author Axel Sanchez
 */
class SearchViewModel @ViewModelInject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private var query: String = ""
    fun setQuery(newQuery: String){
        query = newQuery
    }

    private val listData: MutableLiveData<List<Product?>?> by lazy {
        MutableLiveData<List<Product?>?>().also {
            getSearch()
        }
    }

    private fun setListData(listProduct: List<Product?>?) {
        listData.value = listProduct
    }

    private fun getSearch() {
        viewModelScope.launch {
            setListData(searchUseCase.getProductsBySearch(query))
        }
    }

    fun getSearchLiveData(): LiveData<List<Product?>?> {
        return listData
    }
}