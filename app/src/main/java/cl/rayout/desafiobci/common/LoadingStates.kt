package cl.rayout.desafiobci.common

import cl.rayout.desafiobci.common.enums.CustomLoadingErrors

sealed class LoadingStates {
    data class Loading(
        val isAdditional: Boolean = false
    ) : LoadingStates()

    data class Error(
        val isAdditional: Boolean = false,
        val errorType: CustomLoadingErrors
    ) : LoadingStates()

    data class Success(
        val isAdditional: Boolean = false
    ) : LoadingStates()
}