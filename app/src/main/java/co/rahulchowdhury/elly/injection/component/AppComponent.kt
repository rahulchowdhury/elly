package co.rahulchowdhury.elly.injection.component

import android.app.Application
import co.rahulchowdhury.elly.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: Application)
}
