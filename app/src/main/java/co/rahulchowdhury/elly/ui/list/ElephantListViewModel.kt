package co.rahulchowdhury.elly.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.repo.ElephantRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class ElephantListViewModel(
    private val elephantRepository: ElephantRepository
) : ViewModel() {
    private val _elephants: MutableLiveData<List<Elephant>> = MutableLiveData()
    val elephants: LiveData<List<Elephant>> = _elephants

    private val _networkError: MutableLiveData<Boolean> = MutableLiveData(false)
    val networkError: LiveData<Boolean> = _networkError

    fun loadElephants() {
        viewModelScope.launch {
            try {
                _elephants.value = elephantRepository.getElephants()
            } catch (unknownHostException: UnknownHostException) {
                _networkError.value = true
            }
        }
    }
}
