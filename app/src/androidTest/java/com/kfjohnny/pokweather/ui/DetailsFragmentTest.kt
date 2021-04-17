package com.kfjohnny.pokweather.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.ui.description.DetailsFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Created by Johnnylee Rocha (kfjohnny2) on 4/16/2021.
 */
@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {
    @Before
    fun setupFragment(){
        val fragmentArgs = Bundle(1).apply {
            putString("pokemonId", "2")
        }
        launchFragmentInContainer<DetailsFragment>(
            fragmentArgs
        )
    }

    @Test fun testEventFragment(){
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(withId(R.id.txtPokemonName)).check(
            ViewAssertions.matches(withText("Ivysaur"))
        )
    }
}