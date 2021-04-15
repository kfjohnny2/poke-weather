package com.kfjohnny.pokweather.ui.description

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
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.model.types.Types
import com.kfjohnny.pokweather.ui.description.adapter.MovesAdapter
import com.kfjohnny.pokweather.util.enum_classes.PokemonTypeResourceEnum
import com.kfjohnny.pokweather.util.extensions.setDrawableFromId
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author Johnnylee Rocha (kfjohnny2) 15/02/2021
 *
 * [DetailsFragment] for retrieving selected pokemon data
 */
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private var filters: MutableList<PokemonTypeResourceEnum> = PokemonTypeResourceEnum.values().toMutableList()

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
            with(pokemon.types){
                binding.ivType.setDrawableFromId(getPokemonTypesResId(this)[0].resId)
                if(this?.size!! > 1){
                    binding.ivType2.visibility = View.VISIBLE
                    binding.ivType2.setDrawableFromId(getPokemonTypesResId(this)[1].resId)
                }

            }
            binding.sflMainShimmer.stopShimmer()
            binding.sflMainShimmer.hideShimmer()
        })
    }

    private fun applySilhuetteToImageView(){
        val matrix = ColorMatrix()
        matrix.setScale(0f, 0f, 0f, 100f)
        val colorFilter = ColorMatrixColorFilter(matrix)
        binding.ivPokemonImages.colorFilter= colorFilter
    }

    private fun configuraRecyclerView(list: List<Moves>) {
        binding.rvMoves.adapter = MovesAdapter(list.toMutableList())
        with(binding.rvMoves) {
            layoutManager = LinearLayoutManager(context)
            this.addItemDecoration(DividerItemDecoration(rvMoves.context, LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
        }
    }

    /**
     * Get all types icon resources for imageView
     *
     * @param type List of pokémon types for retrieving resources from PokemonTypeResourceEnum
     *
     * @return List<PokemonTypeResourceEnum> list of type enum
     */
    private fun getPokemonTypesResId(type: List<Types>?) = filters.filter { enum -> type?.any { it.type?.typeName == enum.typeName }!! }
}