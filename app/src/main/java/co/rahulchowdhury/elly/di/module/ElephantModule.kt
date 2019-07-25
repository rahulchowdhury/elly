package co.rahulchowdhury.elly.di.module

import co.rahulchowdhury.elly.data.repo.ElephantRepository
import co.rahulchowdhury.elly.data.source.remote.elephant.ElephantApiService
import co.rahulchowdhury.elly.ui.profile.ElephantProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val elephantModule = module {
    single { provideElephantRepository(get()) }
    viewModel { provideElephantProfileViewModel(get()) }
}

fun provideElephantRepository(elephantApiService: ElephantApiService) =
    ElephantRepository(elephantApiService)

fun provideElephantProfileViewModel(elephantRepository: ElephantRepository) =
    ElephantProfileViewModel(elephantRepository)
