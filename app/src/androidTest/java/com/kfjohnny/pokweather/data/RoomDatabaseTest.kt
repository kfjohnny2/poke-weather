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
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, PokemonWeatherDatabase::class.java
        ).build()
        pokemonSampleDAO = db.pokemonSampleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    /* ************              PokemonSampleDAO Test Starts here                 ****************/
    @Test
    @Throws(Exception::class)
    fun writePokemonAndReadTest() {
        val pokemonSample = PokemonSample(1, "teste", "urlSample")
        pokemonSampleDAO.createPokemon(pokemonSample)
        val byName = pokemonSampleDAO.findPokemonByName("teste")
        Assert.assertThat(byName.pokemonName, equalTo(pokemonSample.pokemonName))
    }

    @Test
    @Throws(Exception::class)
    fun writeAndListPokemonTest() {
        val pokemonSample = listOf(
            PokemonSample(1, "pikachu", "urlSample1"),
            PokemonSample(2, "bulbasaur", "urlSample2")
        )
        pokemonSampleDAO.saveAll(pokemonSample)
        val pokemonList = pokemonSampleDAO.getPokemonsLocal()
        Assert.assertThat(pokemonList.size, equalTo(2))
    }

    @Test
    @Throws(Exception::class)
    fun writePokemonAndFindByNameOrIdTest() {
        val pokemonSample = listOf(
            PokemonSample(1, "pikachu", "https://pokeapi.co/api/v2/pokemon/1/"),
            PokemonSample(2, "bulbasaur", "https://pokeapi.co/api/v2/pokemon/2/")
        )
        pokemonSampleDAO.saveAll(pokemonSample)
        val byNameStart = pokemonSampleDAO.findPokemonByNameOrID("bul")
        val byNameEnd = pokemonSampleDAO.findPokemonByNameOrID("saur")
        val byId = pokemonSampleDAO.findPokemonByNameOrID("2")
        Assert.assertTrue(byNameStart.isNotEmpty())
        Assert.assertTrue(byNameEnd.isNotEmpty())
        Assert.assertThat(byId[0].pokemonName, equalTo("bulbasaur"))
    }
    /* ************              PokemonSampleDAO Test Ends Here                   ****************/

}