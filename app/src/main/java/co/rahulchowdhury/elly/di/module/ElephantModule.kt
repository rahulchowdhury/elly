package co.rahulchowdhury.elly.di.module

import android.app.Application
import androidx.room.Room
import co.rahulchowdhury.elly.data.repo.ElephantRepository
import co.rahulchowdhury.elly.data.source.local.elephant.ElephantDao
import co.rahulchowdhury.elly.data.source.local.elephant.ElephantDatabase
import co.rahulchowdhury.elly.data.source.remote.elephant.ElephantApiService
import co.rahulchowdhury.elly.ui.profile.ElephantProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val elephantModule = module {
    single { provideElephantDao(get()) }
    single { provideElephantRepository(get(), get()) }
    viewModel { provideElephantProfileViewModel(get()) }
}

fun provideElephantDao(applicationContext: Application) =
    Room.databaseBuilder(
        applicationContext,
        ElephantDatabase::class.java,
        "elephant-database"
    ).build().elephantDao()

fun provideElephantRepository(
    elephantDao: ElephantDao,
    elephantApiService: ElephantApiService
) =
    ElephantRepository(elephantDao, elephantApiService)

fun provideElephantProfileViewModel(elephantRepository: ElephantRepository) =
    ElephantProfileViewModel(elephantRepository)
