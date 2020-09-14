package com.example.mercadolibre.data.room

import androidx.room.TypeConverter
import com.example.mercadolibre.data.models.MyResponse.Producto.*

/**
 * @author Axel Sanchez
 */
class Converters {

    private var vacio = "null"

    @TypeConverter
    fun fromAddress(address: Address?): String? {
        address?.let {
            return "${it.city_id};A${it.city_name};A${it.state_id};A${it.state_name}"
        } ?: return null
    }

    @TypeConverter
    fun toAddress(address: String?): Address? {
        address?.let {
            var listAddress = it.split(";A")
            return Address(if (listAddress[0] == vacio) null else listAddress[0], if (listAddress[1] == vacio) null else listAddress[1], if (listAddress[2] == vacio) null else listAddress[2], if (listAddress[3] == vacio) null else listAddress[3])
        } ?: return null
    }

    @TypeConverter
    fun fromDifferentialPricing(differentialPricing: DifferentialPricing?): Int? {
        return differentialPricing?.let { it.id?.let { id -> id } }
    }

    @TypeConverter
    fun toDifferentialPricing(id: Int?): DifferentialPricing? {
        return DifferentialPricing(id)
    }

    @TypeConverter
    fun fromInstallments(installments: Installments?): String? {
        installments?.let {
            return "${it.amount};I${it.currency_id};I${it.quantity};I${it.rate}"
        } ?: return null
    }

    @TypeConverter
    fun toInstallments(installments: String?): Installments? {
        installments?.let {
            var listInstallments = it.split(";I")
            return Installments(if (listInstallments[0] != vacio) listInstallments[0].toDoubleOrNull() else null, if (listInstallments[1] != vacio) listInstallments[1] else null, if (listInstallments[2] != vacio) listInstallments[2].toIntOrNull() else null, if (listInstallments[3] != vacio) listInstallments[3].toDoubleOrNull() else null)
        } ?: return null
    }

    @TypeConverter
    fun fromOriginalPrice(number: Number?): Int? {
        return number?.let { it.toInt() }
    }

    @TypeConverter
    fun toOriginalPrice(number: Int?): Number? {
        return number?.let { it }
    }

    @TypeConverter
    fun fromTags(tags: List<String?>?): String? {
        var response = ""
        tags?.let {
            for (i in tags.indices) {
                response += if (i == 0) tags[i]
                else ";LT${tags[i]}"
            }
        } ?: return null
        return response
    }

    @TypeConverter
    fun toTags(concat: String?): List<String?>? {
        var list = concat?.split(";LT")
        list?.let {
            return it.map { str -> if (str != vacio) str else null }
        } ?: return null
    }

    @TypeConverter
    fun fromShipping(shipping: Shipping?): String? {
        shipping?.let {
            return "${it.free_shipping};S${it.logistic_type};S${it.mode};S${it.store_pick_up};S${fromTags(it.tags)}"
        } ?: return null
    }

    @TypeConverter
    fun toShipping(string: String?): Shipping? {
        string?.let {
            var listShipping = it.split(";S")
            return Shipping(if (listShipping[0] != vacio) listShipping[0].toBoolean() else null, if (listShipping[1] != vacio) listShipping[1] else null, if (listShipping[2] != vacio) listShipping[2] else null, if (listShipping[3] != vacio) listShipping[3].toBoolean() else null, toTags(listShipping[4]))
        } ?: return null
    }

    @TypeConverter
    fun fromCity(city: SellerAddress.City?): String? {
        city?.let { return "${city.id};CI${city.name}" } ?: return null
    }

    @TypeConverter
    fun toCity(string: String?): SellerAddress.City? {
        string?.let {
            var listCity = it.split(";CI")
            return SellerAddress.City(if (listCity[0] != vacio) listCity[0] else null, if (listCity[1] != vacio) listCity[1] else null)
        } ?: return null
    }

    @TypeConverter
    fun fromCountry(country: SellerAddress.Country?): String? {
        country?.let { return "${country.id};CO${country.name}" } ?: return null
    }

    @TypeConverter
    fun toCountry(string: String?): SellerAddress.Country? {
        string?.let {
            var listCity = it.split(";CO")
            return SellerAddress.Country(if (listCity[0] != vacio) listCity[0] else null, if (listCity[1] != vacio) listCity[1] else null)
        } ?: return null
    }

    @TypeConverter
    fun fromState(state: SellerAddress.State?): String? {
        state?.let { return "${state.id};SA${state.name}" } ?: return null
    }

    @TypeConverter
    fun toState(string: String?): SellerAddress.State? {
        string?.let {
            var listCity = string.split(";SA")
            return SellerAddress.State(if (listCity[0] != vacio) listCity[0] else null, if (listCity[1] != vacio) listCity[1] else null)
        } ?: return null
    }

    @TypeConverter
    fun fromAttribute(attribute: Attribute?): String? {
        attribute?.let {
            var toString = "${it.attribute_group_id};A${it.attribute_group_id};A${it.id};A${it.name};A${it.source};A${it.value_id};A${it.value_name};A" + "${fromValueStruct(it.value_struct)};A${fromValueList(it.values)}"
            println("toString: $toString")
            return toString
        } ?: return null
    }

    @TypeConverter
    fun toAttribute(str: String?): Attribute? {
        str?.let {
            var listAttribute = it.split(";A")
            return Attribute(if (listAttribute[0] != vacio) listAttribute[0] else null, if (listAttribute[1] != vacio) listAttribute[1] else null, if (listAttribute[2] != vacio) listAttribute[2] else null, if (listAttribute[3] != vacio) listAttribute[3] else null, if (listAttribute[4] != vacio) listAttribute[4].toLongOrNull() else null, if (listAttribute[5] != vacio) listAttribute[5] else null, if (listAttribute[6] != vacio) listAttribute[6] else null, toValueStruct(listAttribute[7]), toValueList(listAttribute[8]))
        } ?: return null
    }

    @TypeConverter
    fun fromValueStruct(valueStruct: Attribute.ValueStruct?): String? {
        valueStruct?.let {
            return "${it.number};VS${it.unit}"
        } ?: return null
    }

    @TypeConverter
    fun toValueStruct(str: String?): Attribute.ValueStruct? {
        str?.let {
            return if(it != "null"){
                var listValueStruct = it.split(";VS")
                Attribute.ValueStruct(if (listValueStruct[0] != vacio) listValueStruct[0].toFloatOrNull() else null, if (listValueStruct[1] != vacio) listValueStruct[1] else null)
            } else null
        } ?: return null
    }

    @TypeConverter
    fun fromAttributeList(list: List<Attribute?>?): String? {
        var response = ""
        list?.let {
            for (i in list.indices) {
                response += if (i == 0) fromAttribute(it[i])
                else ";AL${fromAttribute(it[i])}"
            }
        } ?: return null
        return response
    }

    @TypeConverter
    fun toAttributeList(concat: String?): List<Attribute?>? {
        var newList = concat?.split(";AL")
        newList?.let {
            return it.map { str -> toAttribute(str) }
        } ?: return null
    }

    @TypeConverter
    fun fromValueList(list: List<Attribute.Value?>?): String? {
        var response = ""
        list?.let {
            for (i in list.indices) {
                response += if (i == 0) fromValue(list[i])
                else ";VL${fromValue(list[i])}"
            }
        } ?: return null
        return response
    }

    @TypeConverter
    fun toValueList(concat: String?): List<Attribute.Value?>? {
        var newList = concat?.split(";VL")
        newList?.let {
            return it.map { str -> toValue(str) }
        } ?: return null
    }

    @TypeConverter
    fun fromValue(value: Attribute.Value?): String? {
        value?.let {
            return "${it.id};V${it.name};V${it.source};V${fromValueStruct(it.struct)}"
        } ?: return null
    }

    @TypeConverter
    fun toValue(str: String?): Attribute.Value? {
        str?.let {
            var listSellerAddress = it.split(";V")
            return Attribute.Value(if (listSellerAddress[0] != vacio) listSellerAddress[0] else null, if (listSellerAddress[1] != vacio) listSellerAddress[1] else null, if (listSellerAddress[2] != vacio) listSellerAddress[2].toLongOrNull() else null, toValueStruct(listSellerAddress[3]))
        } ?: return null
    }

    @TypeConverter
    fun fromSellerAddress(sellerAddress: SellerAddress?): String? {
        sellerAddress?.let {
            return "${fromCity(it.city)};SA${it.comment};SA${fromCountry(it.country)};SA${it.id};SA${it.latitude};SA${it.longitude};SA${fromState(it.state)};SA${it.zip_code}"
        } ?: return null
    }

    @TypeConverter
    fun toSellerAddress(str: String?): SellerAddress? {
        str?.let {
            var listSellerAddress = it.split(";SA")
            return SellerAddress(if (listSellerAddress[0] != vacio) listSellerAddress[0] else null, toCity(listSellerAddress[1]), if (listSellerAddress[2] != vacio) listSellerAddress[2] else null, toCountry(listSellerAddress[3]), if (listSellerAddress[4] != vacio) listSellerAddress[4] else null, if (listSellerAddress[5] != vacio) listSellerAddress[5] else null, if (listSellerAddress[6] != vacio) listSellerAddress[6] else null, toState(listSellerAddress[7]), if (listSellerAddress[8] != vacio) listSellerAddress[8] else null)
        } ?: return null
    }

    @TypeConverter
    fun fromSeller(seller: Seller?): String? {
        seller?.let {
            return "${it.car_dealer};S${fromEShop(it.eshop)};S${it.id};S${it.permalink};S${it.real_estate_agency};S${it.registration_date};S${fromSellerReputation(it.seller_reputation)}" + ";S${fromTags(it.tags)}"
        } ?: return null
    }

    @TypeConverter
    fun toSeller(str: String?): Seller? {
        str?.let {
            var listSeller = it.split(";S")
            return Seller(if (listSeller[0] != vacio) listSeller[0].toBoolean() else null, toEShop(listSeller[1]), if (listSeller[2] != vacio) listSeller[2].toIntOrNull() else null, if (listSeller[3] != vacio) listSeller[3] else null, if (listSeller[4] != vacio) listSeller[4].toBoolean() else null, if (listSeller[5] != vacio) listSeller[5] else null, toSellerReputation(listSeller[6]), toTags(listSeller[7]))
        } ?: return null
    }

    @TypeConverter
    fun fromSellerReputation(sellerReputation: Seller.SellerReputation?): String? {
        sellerReputation?.let {
            return "${it.level_id};SR${fromMetrics(it.metrics)};SR${it.power_seller_status};SR${fromTransactions(it.transactions)}"
        } ?: return null
    }

    @TypeConverter
    fun toSellerReputation(str: String?): Seller.SellerReputation? {
        str?.let {
            var listSellerReputation = it.split(";SR")
            return Seller.SellerReputation(if(listSellerReputation[0] != vacio) listSellerReputation[0] else null,
                toMetrics(listSellerReputation[1]),
                if(listSellerReputation[2] != vacio) listSellerReputation[2] else null,
                toTransactions(listSellerReputation[3]))
        } ?: return null
    }

    @TypeConverter
    fun fromTransactions(transactions: Seller.SellerReputation.Transactions?): String? {
        transactions?.let {
            return "${it.canceled};T${it.completed};T${it.period};T${fromRatings(it.ratings)};T${it.total}"
        } ?: return null
    }

    @TypeConverter
    fun toTransactions(str: String?): Seller.SellerReputation.Transactions? {
        str?.let {
            var listAddress = it.split(";T")
            return Seller.SellerReputation.Transactions(if(listAddress[0] != vacio) listAddress[0].toIntOrNull() else null,
                if(listAddress[1] != vacio) listAddress[1].toIntOrNull() else null,
                if(listAddress[2] != vacio) listAddress[2] else null,
                toRatings(listAddress[3]),
                if(listAddress[4] != vacio) listAddress[4].toIntOrNull() else null)
        } ?: return null
    }

    @TypeConverter
    fun fromRatings(ratings: Seller.SellerReputation.Transactions.Ratings?): String? {
        ratings?.let {
            return "${it.negative};R${it.neutral};R${it.positive}"
        } ?: return null
    }

    @TypeConverter
    fun toRatings(str: String?): Seller.SellerReputation.Transactions.Ratings? {
        str?.let {
            var listAddress = it.split(";R")
            return Seller.SellerReputation.Transactions.Ratings(if(listAddress[0] != vacio) listAddress[0].toDoubleOrNull() else null,
                if(listAddress[1] != vacio) listAddress[1].toDoubleOrNull() else null,
                if (listAddress[2] != vacio) listAddress[2].toDoubleOrNull() else null)
        } ?: return null
    }

    @TypeConverter
    fun fromMetrics(metrics: Seller.SellerReputation.Metrics?): String? {
        metrics?.let {
            return "${fromCancellations(it.cancellations)};M${fromClaims(it.claims)};M${fromDelayedHandlingTime(it.delayed_handling_time)};M${fromSales(it.sales)}"
        } ?: return null
    }

    @TypeConverter
    fun toMetrics(str: String?): Seller.SellerReputation.Metrics? {
        str?.let {
            var listMetrics = it.split(";M")
            return Seller.SellerReputation.Metrics(toCancellations(listMetrics[0]), toClaims(listMetrics[1]), toDelayedHandlingTime(listMetrics[2]), toSales(listMetrics[3]))
        } ?: return null
    }

    @TypeConverter
    fun fromCancellations(cancellations: Seller.SellerReputation.Metrics.Cancellations?): String? {
        cancellations?.let {
            return "${it.period};C${it.rate};C${it.value}"
        } ?: return null
    }

    @TypeConverter
    fun toCancellations(str: String?): Seller.SellerReputation.Metrics.Cancellations? {
        str?.let {
            var listAddress = it.split(";C")
            return Seller.SellerReputation.Metrics.Cancellations(
                if(listAddress[0] != vacio) listAddress[0] else null,
                if(listAddress[1] != vacio) listAddress[1].toDoubleOrNull() else null,
                if(listAddress[2] != vacio) listAddress[2].toIntOrNull() else null)
        } ?: return null
    }

    @TypeConverter
    fun fromClaims(claims: Seller.SellerReputation.Metrics.Claims?): String? {
        claims?.let {
            return "${it.period};CL${it.rate};CL${it.value}"
        } ?: return null
    }

    @TypeConverter
    fun toClaims(str: String?): Seller.SellerReputation.Metrics.Claims? {
        str?.let {
            var listAddress = it.split(";CL")
            return Seller.SellerReputation.Metrics.Claims(
                if(listAddress[0] != vacio) listAddress[0] else null,
                if(listAddress[1] != vacio) listAddress[1].toDoubleOrNull() else null,
                if(listAddress[2] != vacio) listAddress[2].toIntOrNull() else null)
        } ?: return null
    }

    @TypeConverter
    fun fromDelayedHandlingTime(delayed: Seller.SellerReputation.Metrics.DelayedHandlingTime?): String? {
        delayed?.let {
            return "${it.period};D${it.rate};D${it.value}"
        } ?: return null
    }

    @TypeConverter
    fun toDelayedHandlingTime(str: String?): Seller.SellerReputation.Metrics.DelayedHandlingTime? {
        str?.let {
            var listAddress = it.split(";D")
            return Seller.SellerReputation.Metrics.DelayedHandlingTime(
                if(listAddress[0] != vacio) listAddress[0] else null,
                if(listAddress[1] != vacio) listAddress[1].toDoubleOrNull() else null,
                if(listAddress[2] != vacio) listAddress[2].toIntOrNull() else null)
        } ?: return null
    }

    @TypeConverter
    fun fromSales(sales: Seller.SellerReputation.Metrics.Sales?): String? {
        sales?.let {
            return "${it.completed};SA${it.period}"
        } ?: return null
    }

    @TypeConverter
    fun toSales(str: String?): Seller.SellerReputation.Metrics.Sales? {
        str?.let {
            var listAddress = it.split(";SA")
            return Seller.SellerReputation.Metrics.Sales(
                if(listAddress[0] != vacio) listAddress[0].toIntOrNull() else null,
                if(listAddress[1] != vacio) listAddress[1] else null)
        } ?: return null
    }

    @TypeConverter
    fun fromEShopRubro(eshopRubro: Seller.Eshop.EShopRubro?): String? {
        eshopRubro?.let {
            return "${it.id};ESR${it.name};ESR${it.category_id}"
        } ?: return null
    }

    @TypeConverter
    fun toEShopRubro(str: String?): Seller.Eshop.EShopRubro? {
        str?.let {
            var listEshopRubro = str.split(";ESR")
            return Seller.Eshop.EShopRubro(
                if(listEshopRubro[0] != vacio) listEshopRubro[0] else null,
                if(listEshopRubro[1] != vacio) listEshopRubro[1] else null,
                if(listEshopRubro[2] != vacio) listEshopRubro[2] else null)
        } ?: return null
    }

    @TypeConverter
    fun fromEShop(eshop: Seller.Eshop?): String? {
        eshop?.let {
            return "${it.eshop_experience};ES${it.eshop_id};ES${fromEShopList(it.eshop_locations)};ES${it.eshop_logo_url};ES${fromEShopRubro(it.eshop_rubro)};ES${it.eshop_status_id};ES" + "${it.nick_name};ES${it.seller};ES${it.site_id}"
        } ?: return null
    }

    @TypeConverter
    fun toEShop(str: String?): Seller.Eshop? {
        str?.let {
            return if (it != vacio) {
                var listEshop = it.split(";ES")
                Seller.Eshop(
                     if(listEshop[0] != vacio) listEshop[0].toIntOrNull() else null,
                     if(listEshop[1] != vacio) listEshop[1].toIntOrNull() else null,
                     toEShopList(listEshop[2]),
                     if(listEshop[3] != vacio) listEshop[3] else null,
                     toEShopRubro(listEshop[4]),
                     if(listEshop[5] != vacio) listEshop[5].toIntOrNull() else null,
                     if(listEshop[6] != vacio) listEshop[6] else null,
                     if(listEshop[7] != vacio) listEshop[7].toIntOrNull() else null,
                     if(listEshop[8] != vacio) listEshop[8] else null)
            } else null
        } ?: return null
    }

    @TypeConverter
    fun fromEShopList(list: List<Seller.Eshop.EshopLocation?>?): String? {
        var response = ""
        list?.let {
            for (i in list.indices) {
                response += if (i == 0) fromEShopLocation(list[i])
                else ";ESL${fromEShopLocation(list[i])}"
            }
        } ?: return null
        return response
    }

    @TypeConverter
    fun toEShopList(concat: String?): List<Seller.Eshop.EshopLocation?>? {
        var newList = concat?.split(";ESL")
        newList?.let {
            return it.map { str -> toEShopLocation(str) }
        } ?: return null
    }

    @TypeConverter
    fun fromEShopLocation(eshopLocation: Seller.Eshop.EshopLocation?): String? {
        eshopLocation?.let {
            var str = "${fromCityEshop(it.city)};ESL${fromCountryEshop(it.country)};ESL${fromNeighborhoodEshop(it.neighborhood)};ESL${fromStateEshop(it.state)}"
            println("el str es: $str")
            return str
        } ?: return null
    }

    @TypeConverter
    fun toEShopLocation(str: String?): Seller.Eshop.EshopLocation? {
        str?.let {
            println("el str devuelto es: $it")
            var listEshopLocation = it.split(";ESL")
            return Seller.Eshop.EshopLocation(toCityEshop(listEshopLocation[0]), toCountryEshop(listEshopLocation[1]), toNeighborhoodEshop(listEshopLocation[2]), toStateEshop(listEshopLocation[3]))
        } ?: return null
    }

    @TypeConverter
    fun fromNeighborhoodEshop(neighborhood: Seller.Eshop.EshopLocation.Neighborhood?): String? {
        return neighborhood?.let { it.id }
    }

    @TypeConverter
    fun toNeighborhoodEshop(id: String?): Seller.Eshop.EshopLocation.Neighborhood? {
        return Seller.Eshop.EshopLocation.Neighborhood(id)
    }

    @TypeConverter
    fun fromCityEshop(city: Seller.Eshop.EshopLocation.City?): String? {
        return city?.let { it.id }
    }

    @TypeConverter
    fun toCityEshop(id: String?): Seller.Eshop.EshopLocation.City? {
        return Seller.Eshop.EshopLocation.City(id)
    }

    @TypeConverter
    fun fromCountryEshop(country: Seller.Eshop.EshopLocation.Country?): String? {
        return country?.let { it.id }
    }

    @TypeConverter
    fun toCountryEshop(id: String?): Seller.Eshop.EshopLocation.Country? {
        return Seller.Eshop.EshopLocation.Country(id)
    }

    @TypeConverter
    fun fromStateEshop(state: Seller.Eshop.EshopLocation.State?): String? {
        return state?.let { it.id }
    }

    @TypeConverter
    fun toStateEshop(id: String?): Seller.Eshop.EshopLocation.State? {
        return Seller.Eshop.EshopLocation.State(id)
    }
}