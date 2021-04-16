package com.kfjohnny.pokweather.ui.pokemon_pager.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kfjohnny.pokweather.R

const val ARG_POKEMON_ID = "pokemonId"

/**
 * @author Created by Johnnylee Rocha (kfjohnny2) on 4/16/2021.
 */
class PokemonSelectedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pokemon_selected, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_POKEMON_ID) }?.apply {
            val textView: TextView = view.findViewById(R.id.text1)
            textView.text = getInt(ARG_POKEMON_ID).toString()
        }
    }
}