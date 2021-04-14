package com.kfjohnny.pokweather.ui.description

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentDetailsBinding
import com.kfjohnny.pokweather.model.moves.Moves
import com.kfjohnny.pokweather.ui.description.adapter.MovesAdapter
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author Johnnylee Rocha (kfjohnny2) 15/02/2021
 *
 * [DetailsFragment] for retrieving selected pokemon data
 */
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_details

    private val detailsViewModel by viewModel<DetailsViewModel>()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.sflMainShimmer.startShimmer()
        val pokemonId = args.pokemonId

        detailsViewModel.loadPokemon(pokemonId)

        binding.detailsViewModel = detailsViewModel

        configureLiveData()

        return binding.root
    }

    private fun configureLiveData() {
        detailsViewModel.pokemonData.observe(viewLifecycleOwner, Observer {pokemon ->
            //Changing background color dynamically by the pokemon dominant color
            pokemon.moves?.let { listMoves -> configuraRecyclerView(listMoves) }
            binding.sflMainShimmer.stopShimmer()
            binding.sflMainShimmer.hideShimmer()
        })
    }

    private fun configuraRecyclerView(list: List<Moves>) {
        binding.rvMoves.adapter = MovesAdapter(list.toMutableList())
        with(binding.rvMoves) {
            layoutManager = LinearLayoutManager(context)
            this.addItemDecoration(DividerItemDecoration(rvMoves.context, LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
        }
    }

}