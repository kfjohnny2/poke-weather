package com.kfjohnny.pokweather.model.pokemon

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.form.Form
import com.kfjohnny.pokweather.model.game_indices.GameIndice
import com.kfjohnny.pokweather.model.location.LocationAreaEncounter
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
    val pokemonName: String,
    @SerializedName(BASE_EXPERIENCE_FIELD)
    val baseExperience: Int,
    val height: Int,
    @SerializedName(IS_DEFAULT_FIELD)
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<Ability>,
    val forms: List<Form>,
    @SerializedName(GAME_INDICES_FIELD)
    val gameIndices: List<GameIndice>,
    @SerializedName(HELD_ITEMS_FIELD)
    val heldItems: List<HeldItem>,
    @SerializedName(LOCATION_AREA_ENCOUNTERS_FIELD)
    val locationAreaEncounters: String,
    val moves : List<Moves>,
    val species : Species,
    val sprites : Sprites,
    val stats : List<Stats>,
    val types : List<Types>
)