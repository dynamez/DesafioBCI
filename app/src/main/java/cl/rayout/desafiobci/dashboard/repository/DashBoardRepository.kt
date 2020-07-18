package cl.rayout.desafiobci.dashboard.repository

import cl.rayout.desafiobci.common.repository.BaseRepository
import cl.rayout.desafiobci.dashboard.network.PokemonNetworkInterface
import timber.log.Timber

class DashBoardRepository(private val pokemonNetworkInterface: PokemonNetworkInterface) :
    BaseRepository() {

    suspend fun getAllPokemons() {
        val results = safeApiCall(
            call = { pokemonNetworkInterface.getAllPokemons() },
            errorMessage = ""
        )
        Timber.d(results.toString())
    }

}