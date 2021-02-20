package com.kfjohnny.pokweather.ui.description.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.databinding.ItemAbilityBinding
import com.kfjohnny.pokweather.model.pokemon.Ability
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.util.helpers.AdapterItemsContract

class AbilitiesAdapter(private var abilities : MutableList<Ability>) : RecyclerView.Adapter<AbilitiesAdapter.AbilitiesViewHolder>(), AdapterItemsContract {
    class AbilitiesViewHolder(val binding : ItemAbilityBinding) : RecyclerView.ViewHolder(binding.root) {
        private val abilityViewModel = AbilityItemViewModel()
        fun bind(ability: Ability){
            abilityViewModel.bind(ability)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemAbilityBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_ability, parent, false)

        return AbilitiesViewHolder(binding)
    }

    override fun getItemCount(): Int = abilities.size

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        holder.bind(abilities[position])
    }

    override fun replaceItems(list: List<*>) {
        if (abilities.isNullOrEmpty()) {
            abilities = list as MutableList<Ability>
        } else {
            abilities.addAll(list as MutableList<Ability>)
        }
        notifyDataSetChanged()

    }
}