package co.rahulchowdhury.elly.injection.component

import co.rahulchowdhury.elly.injection.module.NetworkModule
import co.rahulchowdhury.elly.ui.profile.ElephantProfileFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [NetworkModule::class])
interface ElephantComponent {
    fun inject(elephantProfileFragment: ElephantProfileFragment)
}
