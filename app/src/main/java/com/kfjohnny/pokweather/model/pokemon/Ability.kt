package com.kfjohnny.pokweather.model.pokemon

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.URL_FIELD
import com.kfjohnny.pokweather.util.IS_HIDDEN_FIELD
import com.kfjohnny.pokweather.util.NAME_FIELD

data class Ability(
    @SerializedName(NAME_FIELD)
    val abilityName: String,
    @SerializedName(URL_FIELD)
    val abilityUrl: String?,
    @SerializedName(IS_HIDDEN_FIELD)
    val isHidden: Boolean,
    val slot: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(abilityName)
        parcel.writeString(abilityUrl)
        parcel.writeByte(if (isHidden) 1 else 0)
        parcel.writeInt(slot)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ability> {
        override fun createFromParcel(parcel: Parcel): Ability {
            return Ability(parcel)
        }

        override fun newArray(size: Int): Array<Ability?> {
            return arrayOfNulls(size)
        }
    }
}