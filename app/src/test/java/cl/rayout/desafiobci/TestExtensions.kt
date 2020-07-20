package cl.rayout.cornershoptest

import androidx.lifecycle.LiveData

//using extensions, we create a call to the livedata observer
fun <T> LiveData<T>.observeOnce(onChangedHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangedHandler)
    observe(observer, observer)
}