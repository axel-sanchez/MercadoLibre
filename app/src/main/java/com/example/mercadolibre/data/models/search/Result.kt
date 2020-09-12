package com.example.mercadolibre.data.models.search

import android.os.Parcel
import android.os.Parcelable

data class Result(
    val accepts_mercadopago: Boolean,
    val address: Address,
    val attributes: List<Attribute>,
    val available_quantity: Int,
    val buying_mode: String,
    val catalog_product_id: Any,
    val category_id: String,
    val condition: String,
    val currency_id: String,
    val differential_pricing: DifferentialPricing,
    val domain_id: String,
    val id: String,
    val installments: Installments,
    val listing_type_id: String,
    val official_store_id: Any,
    val original_price: Number?,
    val permalink: String,
    val price: Number,
    val seller: Seller,
    val seller_address: SellerAddress,
    val shipping: Shipping,
    val site_id: String,
    val sold_quantity: Int,
    val stop_time: String,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    var realPrice: Float = 0.0f,
    var realOriginalPrice: Float = 0.0f
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readParcelable(Address::class.java.classLoader)!!,
        TODO("attributes"),
        parcel.readInt(),
        parcel.readString()!!,
        TODO("catalog_product_id"),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        TODO("differential_pricing"),
        parcel.readString()!!,
        parcel.readString()!!,
        TODO("installments"),
        parcel.readString()!!,
        TODO("official_store_id"),
        TODO("original_price"),
        parcel.readString()!!,
        TODO("price"),
        TODO("seller"),
        TODO("seller_address"),
        TODO("shipping"),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (accepts_mercadopago) 1 else 0)
        parcel.writeParcelable(address, flags)
        parcel.writeInt(available_quantity)
        parcel.writeString(buying_mode)
        parcel.writeString(category_id)
        parcel.writeString(condition)
        parcel.writeString(currency_id)
        parcel.writeString(domain_id)
        parcel.writeString(id)
        parcel.writeString(listing_type_id)
        parcel.writeString(permalink)
        parcel.writeString(site_id)
        parcel.writeInt(sold_quantity)
        parcel.writeString(stop_time)
        parcel.writeStringList(tags)
        parcel.writeString(thumbnail)
        parcel.writeString(title)
        parcel.writeFloat(realPrice)
        parcel.writeFloat(realOriginalPrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}