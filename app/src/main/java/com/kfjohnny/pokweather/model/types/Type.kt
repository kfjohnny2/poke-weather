package com.kfjohnny.pokweather.model.types

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class Type(
    @SerializedName(NAME_FIELD) val typeName: String?,
    @SerializedName(URL_FIELD) val typeUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(typeName)
        parcel.writeString(typeUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Type> {
        override fun createFromParcel(parcel: Parcel): Type {
            return Type(parcel)
        }

        override fun newArray(size: Int): Array<Type?> {
            return arrayOfNulls(size)
        }
    }
}
