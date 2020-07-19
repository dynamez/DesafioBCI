package cl.rayout.desafiobci.common.utils

object StringUtils {

    fun getPokemonIdfromURL(url: String): String = url.substring(
        33
    ).dropLast(1)

    fun getPokeIdFromEvolutionChainURL(url: String): String = url.substring(
        41
    ).dropLast(1)
}