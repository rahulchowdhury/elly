package co.rahulchowdhury.elly.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.repo.ElephantRepository
import co.rahulchowdhury.elly.exception.base.InvalidArgumentException
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class ElephantProfileViewModel(
    private val elephantRepository: ElephantRepository
) : ViewModel() {
    private val _elephant: MutableLiveData<Elephant> = MutableLiveData()
    val elephant: LiveData<Elephant> = _elephant

    private val _networkError: MutableLiveData<Boolean> = MutableLiveData(false)
    val networkError: LiveData<Boolean> = _networkError

    fun loadElephantProfile(elephantName: String) {
        if (elephantName.isEmpty()) {
            throw InvalidArgumentException("Elephant name cannot be blank")
        }

        viewModelScope.launch {
            try {
                _elephant.value = elephantRepository.getElephant(elephantName)
            } catch (unknownHostException: UnknownHostException) {
                _networkError.value = true
            }
        }
    }
}
