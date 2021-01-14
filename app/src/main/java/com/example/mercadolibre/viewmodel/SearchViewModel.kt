package com.example.mercadolibre.viewmodel

import androidx.lifecycle.*
import com.example.mercadolibre.data.models.MyResponse.Product
import com.example.mercadolibre.domain.SearchUseCase
import kotlinx.coroutines.launch

/**
 * View model de [SearchFragment]
 * @author Axel Sanchez
 */
class SearchViewModel(private val searchUseCase: SearchUseCase, private val query: String) : ViewModel() {

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

    class SearchViewModelFactory(private val searchUseCase: SearchUseCase, private val query: String) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(SearchUseCase::class.java, String::class.java).newInstance(searchUseCase, query)
        }
    }
}