package com.kfjohnny.pokweather.ui.pokemon_pager.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author Created by Johnnylee Rocha (kfjohnny2) on 4/16/2021.
 */
class PokemonPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 10

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = PokemonSelectedFragment()
        fragment.arguments = Bundle(1).apply {
            // Our object is just an integer :-P
            putInt(ARG_POKEMON_ID, position + 1)
        }
        return fragment
    }
}