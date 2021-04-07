package com.kfjohnny.pokweather.ui.main

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentMainBinding
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.ui.description.adapter.MovesAdapter
import com.kfjohnny.pokweather.ui.main.adapter.PokemonGridAdapter
import com.kfjohnny.pokweather.util.changeDynamicBackgroundColor
import com.kfjohnny.pokweather.util.extensions.hideKeyboard
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_main

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment

        initViewModel()

        binding.mainViewModel = mainViewModel

        binding.imgPokemonPic.setOnClickListener {
            navigateToDetails()
        }
        configuraRecyclerView()

        return binding.root
    }

    private fun navigateToDetails(){
        val pokemon = mainViewModel.pokemonData.value
        if(pokemon != null) {
            val directions = MainFragmentDirections.actionMainFragmentToDetailsFragment(pokemon)
            binding.root.findNavController().navigate(directions)
        }
    }

    private fun initViewModel() {
        mainViewModel.pokemonData.observe(viewLifecycleOwner, Observer {
            hideKeyboard()
            //Changing background color dynamically by the pokemon dominant color
            activity?.let { it1 -> changeDynamicBackgroundColor(it, it1) }
        })

        // Observe showLoading value and display or hide our activity's progressBar
        mainViewModel.showLoading.observe(viewLifecycleOwner, Observer { showLoading ->
            //mainProgressBar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })
        // Observe showError value and display the error message as a Toast
        mainViewModel.showError.observe(viewLifecycleOwner, Observer { showError ->
            Toast.makeText(context, showError, Toast.LENGTH_SHORT).show()
        })
        // The observers are set, we can now ask API to load a data list
    }

    private fun configuraRecyclerView() {
        binding.rvPokemons.adapter = PokemonGridAdapter(mutableListOf())
        with(binding.rvPokemons) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }
}