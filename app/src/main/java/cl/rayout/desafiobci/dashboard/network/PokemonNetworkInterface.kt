package cl.rayout.desafiobci.dashboard.network

import cl.rayout.desafiobci.BuildConfig
import cl.rayout.desafiobci.pokemondetails.model.PokemonDetailsNetworkResponse
import cl.rayout.desafiobci.pokemondetails.model.PokemonEvolutionChain
import cl.rayout.desafiobci.pokemondetails.model.PokemonSpecies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonNetworkInterface {

    @GET("${BuildConfig.ENDPOINT_VERSION}pokemon?limit=151&offset=0")
    suspend fun getAllPokemons(): Response<AllPokemonResponse>

    @GET("${BuildConfig.ENDPOINT_VERSION}pokemon/{pokeId}/")
    suspend fun getPokemonData(@Path("pokeId") pokeId: String?): Response<PokemonDetailsNetworkResponse>

    @GET("${BuildConfig.ENDPOINT_VERSION}pokemon-species/{pokeId}/")
    suspend fun getPokemonSpecies(@Path("pokeId") pokeId: String?): Response<PokemonSpecies>

    @GET("${BuildConfig.ENDPOINT_VERSION}evolution-chain/{pokeId}/")
    suspend fun getPokemonEvolutionChain(@Path("pokeId") pokeId: String?): Response<PokemonEvolutionChain>
}