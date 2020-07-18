package cl.rayout.desafiobci.common.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.rayout.desafiobci.common.LoadingStates
import cl.rayout.desafiobci.common.enums.CustomErrors
import cl.rayout.desafiobci.common.network.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

open class BaseViewModel : ViewModel() {

    val networkStatus = MutableLiveData<NetworkState>()
    val loadingState = MutableLiveData<LoadingStates>()

    //handler for execeptions while running coroutines
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d("Error ${throwable.localizedMessage}")
        networkStatus.postValue(NetworkState.Error(type = throwable))
        loadingState.postValue(LoadingStates.Error(errorType = CustomErrors.CoroutineError))
    }

}