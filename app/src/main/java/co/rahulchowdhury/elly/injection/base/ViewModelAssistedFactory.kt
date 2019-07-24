package co.rahulchowdhury.elly.injection.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface ViewModelAssistedFactory<T : ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): T
}
