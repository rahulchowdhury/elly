package co.rahulchowdhury.elly

import android.app.Application
import co.rahulchowdhury.elly.di.module.elephantModule
import co.rahulchowdhury.elly.di.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ElephantApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ElephantApp)
            modules(listOf(networkModule, elephantModule))
        }
    }
}
