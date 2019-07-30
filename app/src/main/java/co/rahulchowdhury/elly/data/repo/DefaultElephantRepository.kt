package co.rahulchowdhury.elly.data.repo

import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.model.local.isStale
import co.rahulchowdhury.elly.data.model.remote.toElephant
import co.rahulchowdhury.elly.data.source.local.elephant.ElephantDao
import co.rahulchowdhury.elly.data.source.remote.elephant.ElephantApiService

class DefaultElephantRepository(
    private val elephantDao: ElephantDao,
    private val elephantApiService: ElephantApiService
) : ElephantRepository {
    override suspend fun getElephants(): List<Elephant> {
        return listOf()
    }

    override suspend fun getElephant(elephantName: String): Elephant {
        refreshElephant(elephantName)
        return elephantDao.load(elephantName)
    }

    private suspend fun refreshElephant(elephantName: String) {
        val elephant = elephantDao.hasElephant(elephantName)

        if (elephant == null || elephant.isStale()) {
            val elephantResponse = elephantApiService.fetchElephant(elephantName)
            elephantResponse?.let {
                elephantDao.save(it.toElephant())
            }
        }
    }
}
