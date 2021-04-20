package com.kfjohnny.pokweather.ui.whodatpokemon

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.base.BaseFragment
import com.kfjohnny.pokweather.databinding.FragmentWhoDatPokemonBinding
import com.kfjohnny.pokweather.util.extensions.hideKeyboard
import com.kfjohnny.pokweather.util.setGlideSrc
import kotlinx.android.synthetic.main.fragment_who_dat_pokemon.*
import org.koin.android.viewmodel.ext.android.viewModel

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
                    if (textView.text.toString().equals(whoDatPokemonViewModel.pokemonData.value?.pokemonName, true) ) {
                        binding.lavGuessState.setAnimation("right_guess.json")
                        binding.ivWhoDatPokemon.clearColorFilter()
                    } else {
                        binding.lavGuessState.setAnimation("wrong_guess.json")
                    }
                    binding.lavGuessState.visibility = View.VISIBLE
                    binding.lavGuessState.playAnimation()
                    hideKeyboard()
                    true
                } else -> false

            }
        }

        return binding.root
    }

    private fun initObserver() {
        whoDatPokemonViewModel.pokemonData.observe(viewLifecycleOwner, Observer {
            applySilhuetteToImageView()
        })
    }

    private fun applySilhuetteToImageView() {
        rightPokemonImage = binding.ivWhoDatPokemon.drawable
        val matrix = ColorMatrix()
        matrix.setScale(0f, 0f, 0f, 100f)
        val colorFilter = ColorMatrixColorFilter(matrix)
        binding.ivWhoDatPokemon.colorFilter = colorFilter
    }


}