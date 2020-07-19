package cl.rayout.desafiobci.pokemondetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cl.rayout.desafiobci.common.viewmodels.BaseViewModel
import cl.rayout.desafiobci.pokemondetails.model.PokemonDetailsNetworkResponse
import cl.rayout.desafiobci.pokemondetails.model.PokemonEvolutionChain
import cl.rayout.desafiobci.pokemondetails.model.PokemonSpecies
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

//    fun getPokemonSpecies(){
//        viewModelScope.launch(coroutineExceptionHandler) {
//            withContext(Dispatchers.IO) {
//                pokemonSpeciesResponse.postValue(pokemonDetailRepository.getPokemonSpecies(pokeId))
//            }
//        }
//    }
}