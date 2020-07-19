package cl.rayout.desafiobci.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cl.rayout.desafiobci.common.viewmodels.BaseViewModel
import cl.rayout.desafiobci.dashboard.network.AllPokemonResponse
import cl.rayout.desafiobci.dashboard.repository.DashBoardRepository
import kotlinx.coroutines.launch

class DashboardViewModel(val dashBoardRepository: DashBoardRepository) : BaseViewModel() {
    val resultLiveData: MutableLiveData<AllPokemonResponse?> = MutableLiveData()


    fun getAllPokemon() {
        viewModelScope.launch {
            resultLiveData.postValue(dashBoardRepository.getAllPokemons())
        }
    }
}