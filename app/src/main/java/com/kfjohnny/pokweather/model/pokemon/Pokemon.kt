package com.kfjohnny.pokweather.model.pokemon

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.form.Form
import com.kfjohnny.pokweather.model.game_indices.GameIndice
import com.kfjohnny.pokweather.model.moves.Moves
import com.kfjohnny.pokweather.model.species.Species
import com.kfjohnny.pokweather.model.sprites.Sprites
import com.kfjohnny.pokweather.model.stats.Stats
import com.kfjohnny.pokweather.model.types.Types
import com.kfjohnny.pokweather.util.*

data class Pokemon(
    @SerializedName(ID_FIELD)
    val pokemonId: Int,
    @SerializedName(NAME_FIELD)
    val pokemonName: String?,
    @SerializedName(BASE_EXPERIENCE_FIELD)
    val baseExperience: Int,
    val height: Int,
    @SerializedName(IS_DEFAULT_FIELD)
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<Ability>?,
    val forms: List<Form>,
    @SerializedName(GAME_INDICES_FIELD)
    val gameIndices: List<GameIndice>,
    @SerializedName(HELD_ITEMS_FIELD)
    val heldItems: List<HeldItem>,
    @SerializedName(LOCATION_AREA_ENCOUNTERS_FIELD)
    val locationAreaEncounters: String?,
    val moves: List<Moves>?,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stats>,
    val types: List<Types>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(Ability),
        TODO("forms"),
        TODO("gameIndices"),
        TODO("heldItems"),
        parcel.readString(),
        parcel.createTypedArrayList(Moves),
        TODO("species"),
        TODO("sprites"),
        TODO("stats"),
        TODO("types")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pokemonId)
        parcel.writeString(pokemonName)
        parcel.writeInt(baseExperience)
        parcel.writeInt(height)
        parcel.writeByte(if (isDefault) 1 else 0)
        parcel.writeInt(order)
        parcel.writeInt(weight)
        parcel.writeTypedList(abilities)
        parcel.writeString(locationAreaEncounters)
        parcel.writeTypedList(moves)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}