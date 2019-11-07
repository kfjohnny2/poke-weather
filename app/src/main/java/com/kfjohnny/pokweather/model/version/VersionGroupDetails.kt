package com.kfjohnny.pokweather.model.version

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.moves.MoveLearnMethod
import com.kfjohnny.pokweather.util.LEVEL_LEARNED_AT_FIELD
import com.kfjohnny.pokweather.util.MOVE_LEARN_METHOD_FIELD
import com.kfjohnny.pokweather.util.VERSION_GROUP_FIELD

data class VersionGroupDetails (

    @SerializedName(LEVEL_LEARNED_AT_FIELD) val levelLearnedAt : Int,
    @SerializedName(VERSION_GROUP_FIELD) val versionGroup : VersionGroup,
    @SerializedName(MOVE_LEARN_METHOD_FIELD) val moveLearnMethod : MoveLearnMethod

)
