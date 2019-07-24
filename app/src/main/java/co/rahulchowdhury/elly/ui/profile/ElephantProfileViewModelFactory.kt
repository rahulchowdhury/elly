package co.rahulchowdhury.elly.ui.profile

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import co.rahulchowdhury.elly.data.repo.ElephantRepository
import javax.inject.Inject

class ElephantProfileViewModelFactory @Inject constructor(
    private val elephantRepository: ElephantRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = ElephantProfileViewModel(handle, elephantRepository) as T
}
