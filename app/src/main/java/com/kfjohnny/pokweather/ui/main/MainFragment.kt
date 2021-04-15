package com.kfjohnny.pokweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentMainBinding
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.ui.main.adapter.PokemonGridAdapter
import com.kfjohnny.pokweather.util.changeDynamicToolbarBackgroundColor
import kotlinx.android.synthetic.main.item_pokemon_grid.*
import org.koin.android.viewmodel.ext.android.viewModel

/* -- Constant Values --*/
const val GRID_VIEW_SPAN_LIMIT = 2

/**
 * @author Johnnylee Rocha (kfjohnny2) 15/02/2021
 *
 * [MainFragment] subclass for listing and searching pokemons.
 */
class MainFragment : BaseFragment<FragmentMainBinding>(), PokemonGridAdapter.PokemonGridAdapterListener {

    override fun layoutRes(): Int = R.layout.fragment_main

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        // Start observers
        initObservers()

        binding.mainViewModel = mainViewModel

        return binding.root
    }

    private fun navigateToDetails(cardView: View, pokemonId: String) {
        with(MainFragmentDirections.goToDetailsFragment()){
            val pokemonDetailTransitionName = getString(R.string.main_pokemon_card_detail_transition_name)
            val extras = FragmentNavigatorExtras(cardView to pokemonDetailTransitionName)
            this.pokemonId = pokemonId
            binding.root.findNavController().navigate(this, extras)
        }

    }

    /**
     * Function for starting viewModel LiveData Observers functions
     *
     */
    private fun initObservers() {
        with(mainViewModel){
            pokemonData.observe(viewLifecycleOwner, Observer {
                //Changing background color dynamically by the pokemon dominant color
                activity?.let { it1 -> changeDynamicToolbarBackgroundColor(it, it1) }
            })
            pokemonList.observe(viewLifecycleOwner, Observer {
                configuraRecyclerView(it)
            })
            // Observe showLoading value and display or hide our activity's progressBar
            showLoading.observe(viewLifecycleOwner, Observer { showLoading ->
                //mainProgressBar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
            })

            // Observe showError value and display the error message as a Toast
            showError.observe(viewLifecycleOwner, Observer { showError ->
                Toast.makeText(context, showError, Toast.LENGTH_SHORT).show()
            })
            // The observers are set, we can now ask API to load a data list
        }
    }

    private fun configuraRecyclerView(list: List<PokemonSample>) {
        binding.rvPokemons.adapter = PokemonGridAdapter(list.toMutableList(), this)
        with(binding.rvPokemons) {
            layoutManager = GridLayoutManager(context, GRID_VIEW_SPAN_LIMIT)
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        binding.rvPokemons.adapter = null
        super.onDestroy()
    }

    /* ******    Pokemon Grid RecyclerView Listeners *************/
    override fun onPokemonClicked(cardView: View, pokemonId: String) {
        navigateToDetails(cardView, pokemonId)
    }

}