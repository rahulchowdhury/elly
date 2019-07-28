package co.rahulchowdhury.elly.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.repo.ElephantRepository
import kotlinx.coroutines.launch

class ElephantProfileViewModel(
    private val elephantRepository: ElephantRepository
) : ViewModel() {
    private val _elephant: MutableLiveData<Elephant> = MutableLiveData()
    val elephant: LiveData<Elephant> = _elephant

    fun loadElephantProfile(elephantName: String) {
        viewModelScope.launch {
            try {
                _elephant.value = elephantRepository.getElephant(elephantName)
            } catch (exception: Exception) {
                //logDebug("Unable to fetch data")
            }
        }
    }
}
