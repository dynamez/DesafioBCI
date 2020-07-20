package cl.rayout.desafiobci.common.enums

sealed class CustomErrors : CustomLoadingErrors {
    object CoroutineError : CustomLoadingErrors
    object EmptyCounters : CustomLoadingErrors
    object EmptyFields : CustomLoadingErrors
}