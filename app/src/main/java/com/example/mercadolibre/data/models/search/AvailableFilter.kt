package com.example.mercadolibre.data.models.search

import com.example.mercadolibre.data.models.search.Value

data class AvailableFilter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<Value>
)