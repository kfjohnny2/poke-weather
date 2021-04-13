package com.kfjohnny.pokweather.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kfjohnny.pokweather.data.config.PokemonWeatherDatabase
import com.kfjohnny.pokweather.model.search.PokemonSample
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * @author Created by Johnnylee Rocha (kfjohnny2) on 4/12/2021.
 */
@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {
    private lateinit var pokemonSampleDAO: PokemonSampleDAO
    private lateinit var db: PokemonWeatherDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, PokemonWeatherDatabase::class.java).build()
        pokemonSampleDAO = db.pokemonSampleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val pokemonSample = PokemonSample(1, "teste", "urlSample")
        pokemonSampleDAO.createPokemon(pokemonSample)
        val byName = pokemonSampleDAO.findPokemonByName("teste")
        Assert.assertThat(byName.pokemonName, equalTo(pokemonSample.pokemonName))
    }
}