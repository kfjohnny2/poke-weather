package com.kfjohnny.pokweather.model.version

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.moves.MoveLearnMethod
import com.kfjohnny.pokweather.util.LEVEL_LEARNED_AT_FIELD
import com.kfjohnny.pokweather.util.MOVE_LEARN_METHOD_FIELD
import com.kfjohnny.pokweather.util.VERSION_GROUP_FIELD

data class VersionGroupDetails (

    @SerializedName(LEVEL_LEARNED_AT_FIELD) val levelLearnedAt : Int,
    @SerializedName(VERSION_GROUP_FIELD) val versionGroup : VersionGroup ?,
    @SerializedName(MOVE_LEARN_METHOD_FIELD) val moveLearnMethod : MoveLearnMethod ?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(VersionGroup::class.java.classLoader),
        parcel.readParcelable(MoveLearnMethod::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(levelLearnedAt)
        parcel.writeParcelable(versionGroup, flags)
        parcel.writeParcelable(moveLearnMethod, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VersionGroupDetails> {
        override fun createFromParcel(parcel: Parcel): VersionGroupDetails {
            return VersionGroupDetails(parcel)
        }

        override fun newArray(size: Int): Array<VersionGroupDetails?> {
            return arrayOfNulls(size)
        }
    }
}
