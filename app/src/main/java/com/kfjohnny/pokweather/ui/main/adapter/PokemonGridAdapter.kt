package com.kfjohnny.pokweather.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.databinding.ItemPokemonGridBinding
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.util.helpers.AdapterItemsContract
import com.kfjohnny.pokweather.util.helpers.DiffCallback

class PokemonGridAdapter(
    private var pokemons: MutableList<PokemonSample>,
    private val listener: PokemonGridAdapterListener
) : RecyclerView.Adapter<PokemonGridViewHolder>(), AdapterItemsContract {

    interface PokemonGridAdapterListener {
        fun onPokemonClicked(cardView: View, pokemonId: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonGridViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPokemonGridBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_pokemon_grid, parent, false)

        return PokemonGridViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PokemonGridViewHolder, position: Int) {
        val pokemonSample = pokemons[position]
        holder.bind(pokemonSample)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun replaceItems(list: List<*>) {
        val diffCallback = DiffCallback(this.pokemons, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

        if (pokemons.isNullOrEmpty()) {
            pokemons = list.filterIsInstance<PokemonSample>().toMutableList()
        } else {
            pokemons.addAll(list.filterIsInstance<PokemonSample>().toMutableList())
        }

    }

}