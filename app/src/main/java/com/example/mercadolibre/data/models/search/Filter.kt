package com.example.mercadolibre.data.models.search

import com.example.mercadolibre.data.models.search.ValueX

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<ValueX>
)