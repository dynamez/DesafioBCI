package cl.rayout.desafiobci.dashboard.viewmodel

import androidx.lifecycle.viewModelScope
import cl.rayout.desafiobci.common.viewmodels.BaseViewModel
import cl.rayout.desafiobci.dashboard.repository.DashBoardRepository
import kotlinx.coroutines.launch

class DashboardViewModel(val dashBoardRepository: DashBoardRepository) : BaseViewModel() {

    fun getAllPokemon() {
        viewModelScope.launch {
            dashBoardRepository.getAllPokemons()
        }
    }
}