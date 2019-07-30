package co.rahulchowdhury.elly.di.module

import android.app.Application
import androidx.room.Room
import co.rahulchowdhury.elly.data.repo.DefaultElephantRepository
import co.rahulchowdhury.elly.data.repo.ElephantRepository
import co.rahulchowdhury.elly.data.source.local.elephant.ElephantDao
import co.rahulchowdhury.elly.data.source.local.elephant.ElephantDatabase
import co.rahulchowdhury.elly.data.source.local.elephant.PersistedElephantDataSource
import co.rahulchowdhury.elly.data.source.remote.elephant.ElephantApiService
import co.rahulchowdhury.elly.ui.list.ElephantListViewModel
import co.rahulchowdhury.elly.ui.profile.ElephantProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val elephantModule = module {
    single { provideElephantDao(get()) }
    single { providePersistedElephantDataSource(get()) }
    single { provideElephantRepository(get(), get()) }
    viewModel { provideElephantProfileViewModel(get()) }
    viewModel { provideElephantListViewModel(get()) }
}

fun provideElephantDao(
    applicationContext: Application
): ElephantDao =
    Room.databaseBuilder(
        applicationContext,
        ElephantDatabase::class.java,
        "elephant-database"
    ).build().elephantDao()

fun providePersistedElephantDataSource(
    elephantDao: ElephantDao
): PersistedElephantDataSource =
    PersistedElephantDataSource(elephantDao)

fun provideElephantRepository(
    persistedElephantDataSource: PersistedElephantDataSource,
    elephantApiService: ElephantApiService
): ElephantRepository =
    DefaultElephantRepository(persistedElephantDataSource, elephantApiService)

fun provideElephantProfileViewModel(
    elephantRepository: ElephantRepository
): ElephantProfileViewModel =
    ElephantProfileViewModel(elephantRepository)

fun provideElephantListViewModel(
    elephantRepository: ElephantRepository
): ElephantListViewModel =
    ElephantListViewModel(elephantRepository)
