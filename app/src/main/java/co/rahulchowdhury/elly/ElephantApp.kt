package co.rahulchowdhury.elly

import android.app.Application
import co.rahulchowdhury.elly.injection.component.AppComponent
import co.rahulchowdhury.elly.injection.component.DaggerAppComponent
import co.rahulchowdhury.elly.injection.module.AppModule

class ElephantApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}
