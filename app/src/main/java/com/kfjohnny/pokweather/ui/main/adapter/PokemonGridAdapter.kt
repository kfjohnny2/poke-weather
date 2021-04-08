package com.kfjohnny.pokweather.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.databinding.ItemPokemonGridBinding
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.util.helpers.AdapterItemsContract

class PokemonGridAdapter(private var pokemons: MutableList<PokemonSample>, private val onPokemonClickListener : (String) -> Unit) : RecyclerView.Adapter<PokemonGridAdapter.PokemonGridViewHolder>(), AdapterItemsContract {
    class PokemonGridViewHolder(val binding: ItemPokemonGridBinding) : RecyclerView.ViewHolder(binding.root) {
        private val pokemonGridItemViewModel = PokemonGridItemViewModel()
        fun bind(pokemonSample: PokemonSample, onPokemonClickListener: (String) -> Unit) {
            pokemonGridItemViewModel.bind(pokemonSample)
            binding.root.setOnClickListener { onPokemonClickListener(pokemonGridItemViewModel.getPokemonId().value!!)}
            binding.pokemonGridViewModel = pokemonGridItemViewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonGridViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPokemonGridBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_pokemon_grid, parent, false)

        return PokemonGridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonGridViewHolder, position: Int) {
        val pokemonSample = pokemons[position]
        holder.bind(pokemonSample, onPokemonClickListener)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun replaceItems(list: List<*>) {
        if (pokemons.isNullOrEmpty()) {
            pokemons = list as MutableList<PokemonSample>
        } else {
            pokemons.addAll(list as MutableList<PokemonSample>)
        }
        notifyDataSetChanged()

    }

}