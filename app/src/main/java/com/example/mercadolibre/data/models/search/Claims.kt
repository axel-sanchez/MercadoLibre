package com.example.mercadolibre.data.models.search

import com.example.mercadolibre.data.models.search.ExcludedX

data class Claims(
    val excluded: ExcludedX,
    val period: String,
    val rate: Double,
    val value: Int
)