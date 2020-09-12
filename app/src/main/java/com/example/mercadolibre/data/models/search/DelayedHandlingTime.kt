package com.example.mercadolibre.data.models.search

import com.example.mercadolibre.data.models.search.ExcludedXX

data class DelayedHandlingTime(
    val excluded: ExcludedXX,
    val period: String,
    val rate: Double,
    val value: Int
)