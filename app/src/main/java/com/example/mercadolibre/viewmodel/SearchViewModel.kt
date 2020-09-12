package com.example.mercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mercadolibre.data.models.search.ResponseSearch
import com.example.mercadolibre.data.models.search.Result
import com.example.mercadolibre.domain.SearchUseCase

/**
 * View model de [SearchFragment]
 * @author Axel Sanchez
 */
class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val listData = MutableLiveData<List<Result?>?>()

    private fun setListData(listResult: List<Result?>?) {
        listData.value = listResult
    }

    suspend fun getSearch(query: String) {
        setListData(searchUseCase.getSearch(query))
    }

    fun getSearchLiveData(): LiveData<List<Result?>?> {
        return listData
    }
}