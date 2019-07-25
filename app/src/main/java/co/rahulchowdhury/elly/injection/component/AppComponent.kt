package co.rahulchowdhury.elly.injection.component

import android.app.Application
import co.rahulchowdhury.elly.injection.module.AppModule
import co.rahulchowdhury.elly.injection.module.ViewModelAssistedFactoriesModule
import co.rahulchowdhury.elly.injection.scope.App
import dagger.Component

@App
@Component(modules = [AppModule::class, ViewModelAssistedFactoriesModule::class])
interface AppComponent {
    fun inject(application: Application)

    fun plusElephantComponent(): ElephantComponent
}
