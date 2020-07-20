package cl.rayout.desafiobci.pokemondetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import cl.rayout.desafiobci.common.viewmodels.BaseViewModel
import cl.rayout.desafiobci.pokemondetails.model.*
import cl.rayout.desafiobci.pokemondetails.repository.PokemonDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonDetailsViewModel(private val pokemonDetailRepository: PokemonDetailRepository) :
    BaseViewModel() {
    val nameMutable: MutableLiveData<String> = MutableLiveData()
    val pokemonResponse: MutableLiveData<PokemonDetailsNetworkResponse?> = MutableLiveData()
    val pokemonSpeciesResponse: MutableLiveData<PokemonSpecies?> = MutableLiveData()
    val pokemonEvolutionResponse: MutableLiveData<PokemonEvolutionChain?> = MutableLiveData()
    val pokeLocationResponse: MutableLiveData<List<LocationDetails>?> = MutableLiveData()
    val pokemonAbilities = pokemonResponse.map {
        it?.pokemonAbilities
    }
    val pokemonMovesResponse = pokemonResponse.map {
        it?.pokemonMoves
    }
    val currentPokemonTypes = pokemonResponse.map { pokemonDetailsNetworkResponse ->
        pokemonDetailsNetworkResponse?.pokemonTypes?.joinToString(
            separator = ", ",
            transform = { it.pokemonType.name }
        )
    }

    val currentPokemonEvolutions = pokemonEvolutionResponse.map { pokemonEvolutionChain ->
        val list = mutableListOf<String>()
        list.add(pokemonEvolutionChain?.chain?.species?.name ?: "")
        list.addAll(
            recursiveEvolutionSearcher(
                mutableListOf(),
                pokemonEvolutionChain?.chain?.pokeEvolvesTo
            )
        )
        list.joinToString(
            separator = ", ",
            transform = { it }
        )

    }
    var name: String? = ""
    var pokeId: String? = ""
    var pokeUrl: String? = ""
    fun getPokemonData(pokeId: String?) {
        viewModelScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                pokemonResponse.postValue(pokemonDetailRepository.getPokemonDetail(pokeId))
                pokemonSpeciesResponse.postValue(pokemonDetailRepository.getPokemonSpecies(pokeId))
                pokemonEvolutionResponse.postValue(
                    pokemonDetailRepository.getPokemonEvolutionChain(
                        "2"
                    )
                )
            }
        }
    }

    fun getPokemonLocations(path: String?) {
        viewModelScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                pokeLocationResponse.postValue(pokemonDetailRepository.getPokemonLocations(path))
            }
        }
    }

    //    fun getPokemonSpecies(){
//        viewModelScope.launch(coroutineExceptionHandler) {
//            withContext(Dispatchers.IO) {
//                pokemonSpeciesResponse.postValue(pokemonDetailRepository.getPokemonSpecies(pokeId))
//            }
//        }
//    }
    private fun recursiveEvolutionSearcher(
        backupList: MutableList<String>,
        searchedList: List<PokeEvolvesTo>?
    ): MutableList<String> {

        searchedList?.forEach {
            if (it.pokeEvolvesTo != null) {
                backupList.add(it.pokeSpecies.name)
                recursiveEvolutionSearcher(backupList, it.pokeEvolvesTo)
            } else {
                backupList.add(it.pokeSpecies.name)
                return@forEach
            }
        }
        return backupList

    }
}