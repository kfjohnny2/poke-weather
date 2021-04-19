package com.kfjohnny.pokweather.util.enum_classes

import androidx.annotation.DrawableRes
import com.kfjohnny.pokweather.R

/**
 * @author Created by Johnnylee Rocha (kfjohnny2) on 4/14/2021.
 */
enum class PokemonTypeResourceEnum(val typeName: String, @DrawableRes val resId: Int) {
    BUG("bug", R.drawable.ic_fire),
    DARK("dark", R.drawable.ic_dark),
    DRAGON("dragon", R.drawable.ic_dragon),
    ELECTRIC("electric", R.drawable.ic_electric),
    FAIRY("fairy", R.drawable.ic_fairy),
    FIGHT("fight", R.drawable.ic_fight),
    FIRE("fire", R.drawable.ic_fire),
    FLYING("flying", R.drawable.ic_flying),
    GHOST("ghost", R.drawable.ic_ghost),
    GRASS("grass", R.drawable.ic_grass),
    GROUND("ground", R.drawable.ic_ground),
    ICE("ice", R.drawable.ic_ice),
    NORMAL("normal", R.drawable.ic_normal),
    POISON("poison", R.drawable.ic_poison),
    PSYCHIC("psychic", R.drawable.ic_psychic),
    ROCK("rock", R.drawable.ic_rock),
    STEEL("steel", R.drawable.ic_steel),
    WATER("water", R.drawable.ic_water);
}