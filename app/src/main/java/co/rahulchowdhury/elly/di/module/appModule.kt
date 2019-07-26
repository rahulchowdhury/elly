package co.rahulchowdhury.elly.di.module

import org.koin.dsl.module
import java.util.concurrent.Executors

val appModule = module {
    factory { Executors.newFixedThreadPool(4) }
}
