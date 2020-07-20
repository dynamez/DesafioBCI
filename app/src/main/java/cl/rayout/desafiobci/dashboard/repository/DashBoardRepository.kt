package cl.rayout.desafiobci.dashboard.repository

import cl.rayout.desafiobci.common.repository.BaseRepository
import cl.rayout.desafiobci.dashboard.network.AllPokemonResponse
import cl.rayout.desafiobci.dashboard.network.PokemonNetworkInterface
import timber.log.Timber

class DashBoardRepository(private val pokemonNetworkInterface: PokemonNetworkInterface) :
    BaseRepository() {

    suspend fun getAllPokemons(): AllPokemonResponse? {
        val results = safeApiCall(
            call = { pokemonNetworkInterface.getAllPokemons() },
            errorMessage = ""
        )
        return results
        Timber.d(results.toString())
    }

}