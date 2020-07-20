package cl.rayout.desafiobci.common.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.rayout.desafiobci.common.paging.NetworkState
import cl.rayout.desafiobci.common.paging.PagingRequestHelper


private fun getErrorMessage(report: PagingRequestHelper.StatusReport): String {
    return PagingRequestHelper.RequestType.values().mapNotNull {
        report.getErrorFor(it)?.message
    }.first()
}

private fun getExceptionType(report: PagingRequestHelper.StatusReport): Throwable {
    return PagingRequestHelper.RequestType.values().mapNotNull {
        report.getErrorFor(it)
    }.first()
}

fun PagingRequestHelper.createStatusLiveData(): LiveData<NetworkState> {
    val liveData = MutableLiveData<NetworkState>()
    addListener { report ->
        when {
            report.hasRunning() -> liveData.postValue(NetworkState.LOADING)
            report.hasError() -> liveData.postValue(
                NetworkState.error(getErrorMessage(report), getExceptionType(report))
            )
            else -> liveData.postValue(NetworkState.LOADED)
        }
    }
    return liveData
}
