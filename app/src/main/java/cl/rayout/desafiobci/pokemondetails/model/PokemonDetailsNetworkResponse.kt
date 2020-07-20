package cl.rayout.desafiobci.pokemondetails.model

import com.squareup.moshi.Json

//Main data
data class PokemonDetailsNetworkResponse(
    @field:Json(name = "abilities") val pokemonAbilities: List<PokemonAbilities>?,
    @field:Json(name = "types") val pokemonTypes: List<PokemonTypes>?,
    @field:Json(name = "location_area_encounters") val locationAreaEncounters: String?,
    @field:Json(name = "moves") val pokemonMoves: List<PokemonMoves>?,
    @field:Json(name = "species") val pokeSpecies: PokeSpecies,
    val id: String
)

data class PokemonAbilities(
    val ability: Ability,
    @field:Json(name = "is_hidden") val isHidden: Boolean,
    @field:Json(name = "slot") val slot: Int
)

data class Ability(
    val name: String,
    val url: String
)

data class PokemonTypes(
    val slot: Int,
    @field:Json(name = "type") val pokemonType: PokemonType
)

data class PokemonType(
    val name: String,
    val url: String
)

data class PokemonMoves(@field:Json(name = "move") val pokemonMove: PokemonMove)
data class PokemonMove(val name: String, val url: String)
data class LocationAreaEncounters(@field:Json(name = "location_area") val locationAreas: LocationArea?)
data class LocationArea(
    val name: String?,
    val url: String?
)

//Species
data class PokemonSpecies(
    @field:Json(name = "evolution_chain") val evolutionChain: EvolutionChain,
    @field:Json(name = "evolves_from_species") val evolvesFromSpecies: EvolvesFromSpecies
)

data class EvolvesFromSpecies(val name: String?, val url: String?)
data class EvolutionChain(val url: String?)

//Evolution chain
data class PokemonEvolutionChain(val chain: PokeChain)
data class PokeChain(
    val species: PokeSpecies,
    @field:Json(name = "evolves_to") val pokeEvolvesTo: List<PokeEvolvesTo>?
)

data class PokeSpecies(
    val name: String,
    val url: String
)

data class PokeEvolvesTo(
    @field:Json(name = "species") val pokeSpecies: PokeSpecies,
    @field:Json(name = "evolves_to") val pokeEvolvesTo: List<PokeEvolvesTo>?
)

data class PokeLocationList(val locationDetails: List<LocationDetails>)
data class LocationDetails(@field:Json(name = "location_area") val location: Location)
data class Location(
    val name: String?,
    val url: String?
)