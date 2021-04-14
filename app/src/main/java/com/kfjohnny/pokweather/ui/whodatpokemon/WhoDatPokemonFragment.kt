package com.kfjohnny.pokweather.ui.whodatpokemon

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentWhoDatPokemonBinding

class WhoDatPokemonFragment : BaseFragment<FragmentWhoDatPokemonBinding>() {

    override fun layoutRes() = R.layout.fragment_who_dat_pokemon

    companion object {
        fun newInstance() = WhoDatPokemonFragment()
    }

    private lateinit var whoDatPokemonViewModel: WhoDatPokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.whoDatPokemonViewModel = whoDatPokemonViewModel
        return binding.root
    }

}