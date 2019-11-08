package nz.co.trademe.wrapper

import androidx.test.core.app.ActivityScenario.launch
import com.interapp.developer.datawrapper.network.PokemonApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"
/**
Created by Preethi Valsalan on 2019-10-25
 */

fun getRetrofit(): Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.addNetworkInterceptor(logging)
    var jsonBuilder = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(jsonBuilder))
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .build()
}
class ApiTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


  //  @Test
    fun testGetPagedPokemons() {
        val retrofit = getRetrofit()
        val service = retrofit.create(PokemonApi::class.java!!)
        runBlocking {
            launch(mainThreadSurrogate) {
                var response = service.getAllPokemons(10, 10)
                assertTrue(response.totalCount > 0)
            }
        }
    }

    @Test
    fun testGetPokemons() {
        val next = "https://pokeapi.co/api/v2/pokemon/?offset=11&limit=1"
        val retrofit = getRetrofit()
        val service = retrofit.create(PokemonApi::class.java!!)
        runBlocking {
            launch(mainThreadSurrogate) {
                var response = service.getPokemons(next)
                assertTrue(response.totalCount > 0)
            }
        }
    }

}