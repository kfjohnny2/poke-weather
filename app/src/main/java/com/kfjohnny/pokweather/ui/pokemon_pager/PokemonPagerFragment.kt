package com.kfjohnny.pokweather.ui.pokemon_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.ui.pokemon_pager.adapter.PokemonPagerAdapter

class PokemonPagerFragment : Fragment() {

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var pokemonPagerAdapter: PokemonPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pokemonPagerAdapter = PokemonPagerAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        //viewPager.setPageTransformer(ZoomPagerItemTransformer(viewPager,0.85f))
        viewPager.adapter = pokemonPagerAdapter
    }


    class ZoomPagerItemTransformer : ViewPager2.PageTransformer {
        interface ZoomPagerCallback {
            fun onPageScaled(scaleFactor: Float)
        }

        private var minScale: Float
        private var viewPager: ViewPager2
        private var callback: ZoomPagerCallback? = null

        constructor(viewPager: ViewPager2, minScale: Float) {
            this.minScale = minScale
            this.viewPager = viewPager
        }

        constructor(viewPager: ViewPager2, minScale: Float, callback: ZoomPagerCallback?) {
            this.minScale = minScale
            this.viewPager = viewPager
            this.callback = callback
        }

        override fun transformPage(page: View, position: Float) {
            val pageWidth = viewPager.measuredWidth - viewPager.paddingLeft - viewPager.paddingRight
            val paddingLeft = viewPager.paddingLeft
            val transformPos = (page.left - (viewPager.scrollX + paddingLeft)).toFloat() / pageWidth
            if (transformPos < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.scaleX = minScale
                page.scaleY = minScale
            } else if (transformPos <= 1) { // [-1,1]
                //page.setTranslationY(max * (1-Math.abs(transformPos)));
                val normalizedposition2 = Math.abs(Math.abs(position) - 1)
                val calculatedScale = normalizedposition2 / 2 + minScale
                if (calculatedScale >= minScale) {
                    page.scaleX = calculatedScale
                    page.scaleY = calculatedScale
                    if (callback != null) {
                        callback!!.onPageScaled(calculatedScale)
                    }
                }
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                page.scaleX = minScale
                page.scaleY = minScale
            }
        }
    }

}