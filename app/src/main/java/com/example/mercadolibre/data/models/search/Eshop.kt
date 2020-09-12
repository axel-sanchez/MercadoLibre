package com.example.mercadolibre.data.models.search

import com.example.mercadolibre.data.models.search.EshopLocation

data class Eshop(
    val eshop_experience: Int,
    val eshop_id: Int,
    val eshop_locations: List<EshopLocation>,
    val eshop_logo_url: String,
    val eshop_rubro: Any,
    val eshop_status_id: Int,
    val nick_name: String,
    val seller: Int,
    val site_id: String
)