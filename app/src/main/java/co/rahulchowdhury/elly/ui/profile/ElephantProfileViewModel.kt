package co.rahulchowdhury.elly.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.repo.ElephantRepository

class ElephantProfileViewModel(
    private val elephantRepository: ElephantRepository
) : ViewModel() {
    lateinit var elephant: LiveData<Elephant>

    fun loadElephantProfile(elephantName: String) {
        elephant = elephantRepository.getElephant(elephantName)
    }
}
