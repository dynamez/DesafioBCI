package cl.rayout.desafiobci.dashboard.network

import cl.rayout.desafiobci.common.network.RetrofitFactory

object PokemonApiFactory {
    val pokemonApi: PokemonNetworkInterface =
        RetrofitFactory.retrofit().create(PokemonNetworkInterface::class.java)
}