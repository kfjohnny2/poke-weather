package com.kfjohnny.pokweather.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kfjohnny.pokweather.databinding.ItemPokemonGridBinding
import com.kfjohnny.pokweather.model.search.PokemonSample

/**
 * @author Created by Johnnylee Rocha (kfjohnny2) on 4/15/2021.
 */
class PokemonGridViewHolder(
    val binding: ItemPokemonGridBinding,
    listener: PokemonGridAdapter.PokemonGridAdapterListener
) : RecyclerView.ViewHolder(binding.root) {
    private val pokemonGridItemViewModel = PokemonGridItemViewModel()

    init {
        binding.run {
            this.listener = listener
        }
    }

    fun bind(pokemonSample: PokemonSample) {
        pokemonGridItemViewModel.bind(pokemonSample)
        binding.pokemonGridViewModel = pokemonGridItemViewModel
        binding.executePendingBindings()
    }
}