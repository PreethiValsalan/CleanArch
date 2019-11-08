package nz.co.trademe.wrapper

import com.interapp.developer.datawrapper.dataSource.DaggerDataSourceComponent
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

import org.junit.Assert
import org.junit.Test

const val SANDBOX_URL = "https://pokeapi.co/api/v2/pokemon/"
/**
Created by Preethi Valsalan on 2019-10-28
 */
class UcTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Test
    fun testRetrieveClosingSoonListings() {
        var dataSourceComponent = DaggerDataSourceComponent.builder().dataSourceModule(SANDBOX_URL).build();
        runBlocking {
            launch(mainThreadSurrogate) {
                val response = dataSourceComponent.pokemonUseCase().getPokemonListings(1, 10, true)
                Assert.assertTrue(response.totalCount > 0)
            }
        }
    }
}