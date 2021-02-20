package com.kfjohnny.pokweather.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentMainBinding
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
}