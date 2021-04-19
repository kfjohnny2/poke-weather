package com.kfjohnny.pokweather.ui.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentDetailsBinding
import com.kfjohnny.pokweather.model.moves.Moves
import com.kfjohnny.pokweather.model.types.Types
import com.kfjohnny.pokweather.ui.description.adapter.MovesAdapter
import com.kfjohnny.pokweather.ui.main.MainFragmentDirections
import com.kfjohnny.pokweather.util.enum_classes.PokemonTypeResourceEnum
import com.kfjohnny.pokweather.util.extensions.setDrawableFromId
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.viewmodel.ext.android.viewModel

const val INDEX_FIRST_POKEMON_TYPE = 0
const val INDEX_SECOND_POKEMON_TYPE = 1
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
    private val pokemonId : String by lazy(LazyThreadSafetyMode.NONE) { args.pokemonId }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.sflMainShimmer.startShimmer()

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
                binding.ivType.setDrawableFromId(getPokemonTypesResId(this)[INDEX_FIRST_POKEMON_TYPE].resId)
                if(this?.size!! > 1){
                    binding.ivType2.visibility = View.VISIBLE
                    binding.ivType2.setDrawableFromId(getPokemonTypesResId(this)[INDEX_SECOND_POKEMON_TYPE].resId)
                }

            }
            binding.ivPokemonImages.setOnClickListener {
                //TODO: Implement something here later
            }
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

    /**
     * Get all types icon resources for imageView
     *
     * @param type List of pokémon types for retrieving resources from PokemonTypeResourceEnum
     *
     * @return List<PokemonTypeResourceEnum> list of type enum
     */
    private fun getPokemonTypesResId(type: List<Types>?) = filters.filter { enum -> type?.any { it.type?.typeName == enum.typeName }!! }
}