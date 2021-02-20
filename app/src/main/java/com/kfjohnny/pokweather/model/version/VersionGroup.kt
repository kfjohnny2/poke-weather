package com.kfjohnny.pokweather.model.version

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class VersionGroup(
    @SerializedName(NAME_FIELD) val versionGroupName: String?,
    @SerializedName(URL_FIELD) val versionGroupUrl: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(versionGroupName)
        parcel.writeString(versionGroupUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VersionGroup> {
        override fun createFromParcel(parcel: Parcel): VersionGroup {
            return VersionGroup(parcel)
        }

        override fun newArray(size: Int): Array<VersionGroup?> {
            return arrayOfNulls(size)
        }
    }
}
