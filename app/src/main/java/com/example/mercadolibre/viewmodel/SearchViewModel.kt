package com.example.mercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mercadolibre.data.models.MyResponse.Producto
import com.example.mercadolibre.domain.SearchUseCase

/**
 * View model de [SearchFragment]
 * @author Axel Sanchez
 */
class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val listData = MutableLiveData<List<Producto?>?>()

    private fun setListData(listProducto: List<Producto?>?) {
        listData.value = listProducto
    }

    suspend fun getSearch(query: String) {
        setListData(searchUseCase.getSearch(query))
    }

    fun getSearchLiveData(): LiveData<List<Producto?>?> {
        return listData
    }
}