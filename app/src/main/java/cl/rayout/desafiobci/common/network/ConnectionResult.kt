package cl.rayout.desafiobci.common.network

sealed class ConnectionResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : ConnectionResult<T>()
    data class Error(val exception: Exception) : ConnectionResult<Nothing>()
}