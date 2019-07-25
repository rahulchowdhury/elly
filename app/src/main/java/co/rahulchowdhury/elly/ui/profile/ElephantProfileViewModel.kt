package co.rahulchowdhury.elly.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.repo.ElephantRepository
import javax.inject.Inject

class ElephantProfileViewModel @Inject constructor(
    elephantRepository: ElephantRepository
) : ViewModel() {
    private val elephantName: String = "Arjuna"
    val elephant: LiveData<Elephant> = elephantRepository.getElephant(elephantName)
}
