package co.rahulchowdhury.elly

import android.app.Application
import co.rahulchowdhury.elly.di.module.elephantModule
import co.rahulchowdhury.elly.di.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ElephantApp : Application() {
    //lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAppComponent
//            .builder()
//            .appModule(AppModule(this))
//            .build()

        startKoin {
            androidContext(this@ElephantApp)
            modules(listOf(networkModule, elephantModule))
        }
    }
}
