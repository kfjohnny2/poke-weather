package com.kfjohnny.pokweather.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.databinding.ItemMoveBinding
import com.kfjohnny.pokweather.databinding.ItemPokemonBinding
import com.kfjohnny.pokweather.model.moves.Moves
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.ui.description.adapter.MovesAdapter
import com.kfjohnny.pokweather.util.helpers.AdapterItemsContract

class PokemonListAdapter(private var pokemonList : MutableList<PokemonSample>) : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(), AdapterItemsContract{
    class PokemonViewHolder(val binding:ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        private val pokemonItemViewModel = PokemonItemViewModel()
        fun bind (pokemonSample: PokemonSample){
            pokemonItemViewModel.bind(pokemonSample)
            binding.pokemonViewModel = pokemonItemViewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPokemonBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_pokemon, parent, false)

        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun replaceItems(list: List<*>) {
        if (pokemonList.isNullOrEmpty()) {
            pokemonList = list as MutableList<PokemonSample>
        } else {
            pokemonList.addAll(list as MutableList<PokemonSample>)
        }
        notifyDataSetChanged()

    }
}