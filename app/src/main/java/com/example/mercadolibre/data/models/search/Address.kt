package com.example.mercadolibre.data.models.search

import android.os.Parcel
import android.os.Parcelable

data class Address(
    val city_id: String,
    val city_name: String,
    val state_id: String,
    val state_name: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city_id)
        parcel.writeString(city_name)
        parcel.writeString(state_id)
        parcel.writeString(state_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}