package co.rahulchowdhury.elly.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.model.remote.ElephantResponse
import co.rahulchowdhury.elly.data.source.remote.elephant.ElephantApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ElephantRepository @Inject constructor(
    private val elephantApiService: ElephantApiService
) {
    fun getElephant(elephantName: String): LiveData<Elephant> {
        val elephantData = MutableLiveData<Elephant>()

        elephantApiService.fetchElephant(elephantName)
            .enqueue(object : Callback<ElephantResponse> {
                override fun onFailure(call: Call<ElephantResponse>, t: Throwable) {
                    TODO("Error in fetching elephant")
                }

                override fun onResponse(call: Call<ElephantResponse>, response: Response<ElephantResponse>) {
                    val elephantResponse = response.body()

                    elephantResponse?.let {
                        elephantData.value = Elephant(
                            id = it.id,
                            name = it.name,
                            affiliation = it.affiliation,
                            species = it.species,
                            sex = it.sex,
                            isFictional = it.fictional.toBoolean(),
                            dateOfBirth = it.dob,
                            dateOfDeath = it.dod,
                            wikiLink = it.wikilink,
                            image = it.image,
                            note = it.note
                        )
                    }
                }
            })

        return elephantData
    }
}
