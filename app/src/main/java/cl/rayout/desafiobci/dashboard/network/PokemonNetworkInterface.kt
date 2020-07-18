package cl.rayout.desafiobci.dashboard.network

import cl.rayout.desafiobci.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

interface PokemonNetworkInterface {

    @GET("${BuildConfig.ENDPOINT_VERSION}pokemon?limit=151&offset=0")
    suspend fun getAllPokemons(): Response<AllPokemonResponse>

    @GET
    suspend fun getPokemonData(): Response<Void>
}