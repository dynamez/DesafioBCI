package cl.rayout.cornershoptest

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer


/**
 * here we build an observer instance that owns its own life-cycle. After handling the onchange event we will mark the observer life-cycle as destroyed.
 * If we dont do this, by the time we are asserting the livedata, the value of the instance will not be populated and will make out test fail
 */

class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)

    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(t: T) {
        handler(t)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}