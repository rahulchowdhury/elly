package co.rahulchowdhury.elly.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import co.rahulchowdhury.elly.data.model.local.Elephant

class ElephantProfileViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val elephantName: String =
        savedStateHandle["elephantName"] ?: throw IllegalArgumentException("Missing elephant name")
    val elephant: LiveData<Elephant> = TODO()
}
