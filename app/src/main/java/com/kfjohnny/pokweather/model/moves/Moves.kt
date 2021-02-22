package com.kfjohnny.pokweather.model.moves

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.version.VersionGroupDetails
import com.kfjohnny.pokweather.util.VERSION_GROUP_DETAILS_FIELD

data class Moves(
    val move : Move ?,
    @SerializedName(VERSION_GROUP_DETAILS_FIELD) val versionGroupDetails : List<VersionGroupDetails> ?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Move::class.java.classLoader),
        parcel.createTypedArrayList(VersionGroupDetails)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(move, flags)
        parcel.writeTypedList(versionGroupDetails)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Moves> {
        override fun createFromParcel(parcel: Parcel): Moves {
            return Moves(parcel)
        }

        override fun newArray(size: Int): Array<Moves?> {
            return arrayOfNulls(size)
        }
    }
}
