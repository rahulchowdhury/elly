package co.rahulchowdhury.elly.injection.component

import android.app.Application
import co.rahulchowdhury.elly.injection.module.AppModule
import co.rahulchowdhury.elly.injection.scope.App
import dagger.Component

@App
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: Application)

    fun plusElephantComponent(): ElephantComponent
}
