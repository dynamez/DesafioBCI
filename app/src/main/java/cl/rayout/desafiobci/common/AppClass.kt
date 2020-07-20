package cl.rayout.desafiobci.common

import android.app.Application
import cl.rayout.desafiobci.BuildConfig
import cl.rayout.desafiobci.common.di.myModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@AppClass)
            modules(myModules)
        }
    }
}