package co.rahulchowdhury.elly.data.repo

import androidx.lifecycle.LiveData
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.source.local.elephant.ElephantDao
import co.rahulchowdhury.elly.data.source.remote.elephant.ElephantApiService
import java.util.concurrent.ExecutorService

class ElephantRepository(
    private val elephantDao: ElephantDao,
    private val elephantApiService: ElephantApiService,
    private val executor: ExecutorService
) {
    fun getElephant(elephantName: String): LiveData<Elephant> {
        refreshElephant(elephantName)

        return elephantDao.load(elephantName)
    }

    private fun refreshElephant(elephantName: String) {
        executor.execute {
            val elephant = elephantDao.hasElephant(elephantName)

            if (elephant == null) {
                val elephantResponse = elephantApiService.fetchElephant(elephantName).execute().body()
                elephantResponse?.let {
                    elephantDao.save(it.toElephant())
                }
            }
        }
    }
}
