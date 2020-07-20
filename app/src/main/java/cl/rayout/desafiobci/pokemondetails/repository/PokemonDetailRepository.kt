package cl.rayout.desafiobci.pokemondetails.repository

import cl.rayout.desafiobci.common.repository.BaseRepository
import cl.rayout.desafiobci.dashboard.network.PokemonNetworkInterface
import cl.rayout.desafiobci.pokemondetails.model.LocationDetails
import cl.rayout.desafiobci.pokemondetails.model.PokemonDetailsNetworkResponse
import cl.rayout.desafiobci.pokemondetails.model.PokemonEvolutionChain
import cl.rayout.desafiobci.pokemondetails.model.PokemonSpecies
import timber.log.Timber

class PokemonDetailRepository(private val pokemonNetworkInterface: PokemonNetworkInterface) :
    BaseRepository() {

    suspend fun getPokemonDetail(pokeId: String?): PokemonDetailsNetworkResponse? {
        val result = safeApiCall(
            call = { pokemonNetworkInterface.getPokemonData(pokeId = pokeId) },
            errorMessage = "Error trying to get pokemon data"
        )
        Timber.d(result.toString())
        return result

    }

    suspend fun getPokemonEvolutionChain(pokeId: String?): PokemonEvolutionChain? {
        val result = safeApiCall(
            call = { pokemonNetworkInterface.getPokemonEvolutionChain(pokeId = pokeId) },
            errorMessage = "Error trying to get pokemon evolution chain"
        )
        Timber.d(result.toString())
        return result

    }

    suspend fun getPokemonSpecies(pokeId: String?): PokemonSpecies? {
        val result = safeApiCall(
            call = { pokemonNetworkInterface.getPokemonSpecies(pokeId = pokeId) },
            errorMessage = "Error trying to get pokemon evolution chain"
        )
        Timber.d(result.toString())
        return result
    }

    suspend fun getPokemonLocations(path: String?): List<LocationDetails>? {
        val result = safeApiCall(
            call = { pokemonNetworkInterface.getPokemonLocation(path?.trim()) },
            errorMessage = "Error trying to get pokemon evolution chain"
        )
        Timber.d(result.toString())
        return result

    }
}