package com.kfjohnny.pokweather.ui.description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentDetailsBinding
import com.kfjohnny.pokweather.ui.description.adapter.AbilitiesAdapter
import com.kfjohnny.pokweather.ui.main.MainViewModel
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

        val pokemon = args.pokemon
        detailsViewModel.pokemonData.value = pokemon

        binding.detailsViewModel = detailsViewModel

        configuraRecyclerView()
        return binding.root
    }

    private fun configuraRecyclerView() {
        binding.rvAbilities.adapter = AbilitiesAdapter(mutableListOf())
        with(binding.rvAbilities) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

}