package cl.rayout.desafiobci.common.repository

import cl.rayout.desafiobci.common.network.ConnectionResult
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result: ConnectionResult<T> = safeApiResult(call, errorMessage)
        var data: T? = null
        when (result) {
            is ConnectionResult.Success ->
                data = result.data
            is ConnectionResult.Error ->
                Timber.d(result.exception, "1.DataRepository : $errorMessage ")

        }
        return data
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): ConnectionResult<T> {
        //An error in the connection or a bad server response will result in a generic being null

        val response = call.invoke()
        if (response.isSuccessful) return ConnectionResult.Success(response.body())
        return ConnectionResult.Error(IOException("Servidor ha retornado error, Custom ERROR - $errorMessage"))

    }
}