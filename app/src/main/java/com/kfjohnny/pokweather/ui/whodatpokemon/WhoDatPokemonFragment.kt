package com.kfjohnny.pokweather.ui.whodatpokemon

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentWhoDatPokemonBinding
import com.kfjohnny.pokweather.ui.description.DetailsFragment
import com.kfjohnny.pokweather.util.extensions.hideKeyboard
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author Johnnylee Rocha (kfjohnny2) 14/04/2021
 *
 * [WhoDatPokemonFragment] for retrieving selected pokemon data
 *
 */
class WhoDatPokemonFragment : BaseFragment<FragmentWhoDatPokemonBinding>() {

    override fun layoutRes() = R.layout.fragment_who_dat_pokemon

    private val whoDatPokemonViewModel by viewModel<WhoDatPokemonViewModel>()
    private lateinit var rightPokemonImage : Drawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.whoDatPokemonViewModel = whoDatPokemonViewModel

        initObserver()

        binding.edGuessPokemon.setOnEditorActionListener { textView, idAction, _ ->
            when (idAction) {
                EditorInfo.IME_ACTION_NEXT -> {
                    pokemonGuessRule(textView)
                    true
                } else -> false

            }
        }

        return binding.root
    }

    /**
     * Execute rules for check if the guess is right
     *
     * @param textView view with the guess's text
     */
    private fun pokemonGuessRule(textView: TextView) {
        if (textView.text.toString()
                .equals(whoDatPokemonViewModel.pokemonData.value?.pokemonName, true)
        ) {
            binding.lavGuessState.setAnimation(getString(R.string.lottie_right_guess))
            binding.ivWhoDatPokemon.clearColorFilter()
        } else {
            binding.lavGuessState.setAnimation(getString(R.string.lottie_wrong_guess))
        }
        binding.lavGuessState.visibility = View.VISIBLE
        binding.lavGuessState.playAnimation()
        hideKeyboard()
    }

    /**
     * Function for init observers
     */
    private fun initObserver() {
        whoDatPokemonViewModel.pokemonData.observe(viewLifecycleOwner, Observer {
            applySilhuetteToImageView()
        })
    }

    /**
     * Function for applying black filter upon pokemon imageView
     */
    private fun applySilhuetteToImageView() {
        rightPokemonImage = binding.ivWhoDatPokemon.drawable
        val matrix = ColorMatrix()
        matrix.setScale(0f, 0f, 0f, 100f)
        val colorFilter = ColorMatrixColorFilter(matrix)
        binding.ivWhoDatPokemon.colorFilter = colorFilter
    }


}