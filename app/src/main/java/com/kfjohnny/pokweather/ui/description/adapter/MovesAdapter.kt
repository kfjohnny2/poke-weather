package com.kfjohnny.pokweather.ui.description.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.databinding.ItemMoveBinding
import com.kfjohnny.pokweather.model.moves.Move
import com.kfjohnny.pokweather.model.moves.Moves
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.util.helpers.AdapterItemsContract
import com.kfjohnny.pokweather.util.helpers.DiffCallback

class MovesAdapter(private var moves : MutableList<Moves>) : RecyclerView.Adapter<MovesAdapter.MovesViewHolder>(), AdapterItemsContract {
    class MovesViewHolder(val binding : ItemMoveBinding) : RecyclerView.ViewHolder(binding.root) {
        private val movesViewModel = MovesItemViewModel()
        fun bind(move: Move){
            movesViewModel.bind(move)
            binding.movesViewModel = movesViewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMoveBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_move, parent, false)

        return MovesViewHolder(binding)
    }

    override fun getItemCount(): Int = moves.size

    override fun onBindViewHolder(holder: MovesViewHolder, position: Int) {
        moves[position].move?.let { holder.bind(it) }
    }

    override fun replaceItems(list: List<*>) {
        val diffCallback = DiffCallback(moves, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

        if (moves.isNullOrEmpty()) {
            moves = list.filterIsInstance<Moves>().toMutableList()
        } else {
            moves.addAll(list.filterIsInstance<Moves>().toMutableList())
        }
    }
}