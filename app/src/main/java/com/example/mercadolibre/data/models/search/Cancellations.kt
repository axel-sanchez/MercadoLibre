package com.example.mercadolibre.data.models.search

import com.example.mercadolibre.data.models.search.Excluded

data class Cancellations(
    val excluded: Excluded,
    val period: String,
    val rate: Double,
    val value: Int
)