package com.example.mercadolibre.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Clase encargada de almacenar la respuesta de la api para buscar productos
 * @author Axel Sanchez
 * */
@Entity data class MyResponse(
    var available_filters: List<AvailableFilter?>? = null,
    var available_sorts: List<AvailableSort?>? = null,
    var filters: List<Filter?>? = null,
    var paging: Paging? = null,
    var query: String? = null,
    var related_Productos: List<Any?>? = null,
    var results: List<Producto?>? = null,
    var secondary_Productos: List<Any?>? = null,
    var site_id: String? = null,
    var sort: Sort? = null
) {
    @Entity data class AvailableFilter(
        var id: String? = null,
        var name: String? = null,
        var type: String? = null,
        var values: List<Value?>? = null
    ) {
        @Entity data class Value(
            var id: String? = null,
            var name: String? = null,
            var Productos: Int? = null
        )
    }

    @Entity data class AvailableSort(
        var id: String? = null,
        var name: String? = null
    )

    @Entity data class Filter(
        var id: String? = null,
        var name: String? = null,
        var type: String? = null,
        var values: List<Value?>? = null
    ) {
        @Entity data class Value(
            var id: String? = null,
            var name: String? = null,
            var path_from_root: List<PathFromRoot?>? = null
        ) {
            @Entity data class PathFromRoot(
                var id: String? = null,
                var name: String? = null
            )
        }
    }

    @Entity data class Paging(
        var limit: Int? = null,
        var offset: Int? = null,
        var primary_Productos: Int? = null,
        var total: Int? = null
    )

    /**
     * Clase que sirve para almacenar los productos que se reciben de la api
     * @author Axel Sanchez
     * **/
    @Entity data class Producto(
        @PrimaryKey var id: String,
        var accepts_mercadopago: Boolean? = null,
        var address: Address? = null,
        var attributes: List<Attribute?>? = null,
        var available_quantity: Int? = null,
        var buying_mode: String? = null,
        var catalog_product_id: String? = null,
        var category_id: String? = null,
        var condition: String? = null,
        var currency_id: String? = null,
        var differential_pricing: DifferentialPricing? = null,
        var domain_id: String? = null,
        var installments: Installments? = null,
        var listing_type_id: String? = null,
        var official_store_id: Int? = null,
        var original_price: Number? = null,
        var permalink: String? = null,
        var price: Number? = null,
        var seller: Seller? = null,
        var seller_address: SellerAddress? = null,
        var shipping: Shipping? = null,
        var site_id: String? = null,
        var sold_quantity: Int? = null,
        var stop_time: String? = null,
        var tags: List<String?>? = null,
        var thumbnail: String? = null,
        var title: String? = null,
        var search: String? = null
    ) {
        @Entity data class Address(
            var city_id: String? = null,
            var city_name: String? = null,
            var state_id: String? = null,
            var state_name: String? = null
        )

        @Entity data class Attribute(
            var attribute_group_id: String? = null,
            var attribute_group_name: String? = null,
            var id: String? = null,
            var name: String? = null,
            var source: Long? = null,
            var value_id: String? = null,
            var value_name: String? = null,
            var value_struct: ValueStruct? = null,
            var values: List<Value?>? = null
        ) {

            @Entity data class ValueStruct(
                var number: Float? = 0.0f,
                var unit: String? = null
            )

            @Entity data class Value(
                var id: String? = null,
                var name: String? = null,
                var source: Long? = null,
                var struct: ValueStruct? = null
            )
        }

        @Entity data class DifferentialPricing(
            var id: Int? = null
        )

        @Entity data class Installments(
            var amount: Double? = null,
            var currency_id: String? = null,
            var quantity: Int? = null,
            var rate: Double? = null
        )

        @Entity data class Seller(
            var car_dealer: Boolean? = null,
            var eshop: Eshop? = null,
            var id: Int? = null,
            var permalink: String? = null,
            var real_estate_agency: Boolean? = null,
            var registration_date: String? = null,
            var seller_reputation: SellerReputation? = null,
            var tags: List<String?>? = null
        ) {
            @Entity data class Eshop(
                var eshop_experience: Int? = null,
                var eshop_id: Int? = null,
                var eshop_locations: List<EshopLocation?>? = null,
                var eshop_logo_url: String? = null,
                var eshop_rubro: EShopRubro? = null,
                var eshop_status_id: Int? = null,
                var nick_name: String? = null,
                var seller: Int? = null,
                var site_id: String? = null
            ) {

                @Entity data class EShopRubro(
                    var id: String? = null,
                    var name: String? = null,
                    var category_id: String? = null
                )

                @Entity data class EshopLocation(
                    var city: City? = null,
                    var country: Country? = null,
                    var neighborhood: Neighborhood? = null,
                    var state: State? = null
                ) {
                    @Entity data class City(
                        var id: String? = null
                    )

                    @Entity data class Country(
                        var id: String? = null
                    )

                    @Entity data class Neighborhood(
                        var id: String? = null
                    )

                    @Entity data class State(
                        var id: String? = null
                    )
                }
            }

            @Entity data class SellerReputation(
                var level_id: String? = null,
                var metrics: Metrics? = null,
                var power_seller_status: String? = null,
                var transactions: Transactions? = null
            ) {
                @Entity data class Metrics(
                    var cancellations: Cancellations? = null,
                    var claims: Claims? = null,
                    var delayed_handling_time: DelayedHandlingTime? = null,
                    var sales: Sales? = null
                ) {
                    @Entity data class Cancellations(
                        var period: String? = null,
                        var rate: Double? = null,
                        var value: Int? = null
                    )

                    @Entity data class Claims(
                        var period: String? = null,
                        var rate: Double? = null,
                        var value: Int? = null
                    )

                    @Entity data class DelayedHandlingTime(
                        var period: String? = null,
                        var rate: Double? = null,
                        var value: Int? = null
                    )

                    @Entity data class Sales(
                        var completed: Int? = null,
                        var period: String? = null
                    )
                }

                @Entity data class Transactions(
                    var canceled: Int? = null,
                    var completed: Int? = null,
                    var period: String? = null,
                    var ratings: Ratings? = null,
                    var total: Int? = null
                ) {
                    @Entity data class Ratings(
                        var negative: Double? = null,
                        var neutral: Double? = null,
                        var positive: Double? = null
                    )
                }
            }
        }

        @Entity data class SellerAddress(
            var address_line: String? = null,
            var city: City? = null,
            var comment: String? = null,
            var country: Country? = null,
            var id: String? = null,
            var latitude: String? = null,
            var longitude: String? = null,
            var state: State? = null,
            var zip_code: String? = null
        ) {
            @Entity data class City(
                var id: String? = null,
                var name: String? = null
            )

            @Entity data class Country(
                var id: String? = null,
                var name: String? = null
            )

            @Entity data class State(
                var id: String? = null,
                var name: String? = null
            )
        }

        @Entity data class Shipping(
            var free_shipping: Boolean? = null,
            var logistic_type: String? = null,
            var mode: String? = null,
            var store_pick_up: Boolean? = null,
            var tags: List<String?>? = null
        )
    }

    @Entity data class Sort(
        var id: String? = null,
        var name: String? = null
    )
}