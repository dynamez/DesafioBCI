package cl.rayout.desafiobci.dashboard.network

data class AllPokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedResponse>
)

data class NamedResponse(
    val name: String,
    val url: String
)