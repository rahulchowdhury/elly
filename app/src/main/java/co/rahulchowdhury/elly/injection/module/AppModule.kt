package co.rahulchowdhury.elly.injection.module

import android.content.Context
import co.rahulchowdhury.elly.injection.scope.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(
    private val context: Context
) {
    @App
    @Provides
    fun provideContext(): Context = context
}
