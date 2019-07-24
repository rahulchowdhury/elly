package co.rahulchowdhury.elly

import android.app.Application
import co.rahulchowdhury.elly.injection.component.AppComponent

class ElephantApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

    }
}
