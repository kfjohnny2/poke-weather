package com.kfjohnny.pokweather.model.moves

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class MoveLearnMethod(
    @SerializedName(NAME_FIELD) val fieldName: String?,
    @SerializedName(URL_FIELD) val fieldUrl: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fieldName)
        parcel.writeString(fieldUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoveLearnMethod> {
        override fun createFromParcel(parcel: Parcel): MoveLearnMethod {
            return MoveLearnMethod(parcel)
        }

        override fun newArray(size: Int): Array<MoveLearnMethod?> {
            return arrayOfNulls(size)
        }
    }
}
