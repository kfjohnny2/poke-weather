package com.kfjohnny.pokweather.ui.description

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.Swatch
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentDetailsBinding
import com.kfjohnny.pokweather.model.moves.Moves
import com.kfjohnny.pokweather.ui.description.adapter.MovesAdapter
import com.kfjohnny.pokweather.util.changeDynamicBackgroundColor
import org.koin.android.viewmodel.ext.android.viewModel


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_details

    private val detailsViewModel by viewModel<DetailsViewModel>()

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val pokemonId = args.pokemonId

        detailsViewModel.loadPokemon(pokemonId)

        binding.detailsViewModel = detailsViewModel

        configureLiveData()
        return binding.root
    }

    private fun configureLiveData() {
        detailsViewModel.pokemonData.observe(viewLifecycleOwner, Observer {
            //Changing background color dynamically by the pokemon dominant color
            activity?.let { it1 -> changeDynamicBackgroundColor(it, it1, binding.cvBackground) }

            it.moves?.let { it1 -> configuraRecyclerView(it1) }
        })
    }

    private fun configuraRecyclerView(list: List<Moves>) {
        binding.rvMoves.adapter = MovesAdapter(list.toMutableList())
        with(binding.rvMoves) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

}