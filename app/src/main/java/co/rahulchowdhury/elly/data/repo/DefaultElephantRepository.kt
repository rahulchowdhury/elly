package co.rahulchowdhury.elly.data.repo

import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.model.remote.toElephant
import co.rahulchowdhury.elly.data.source.local.elephant.LocalElephantDataSource
import co.rahulchowdhury.elly.data.source.remote.elephant.ElephantApiService

class DefaultElephantRepository(
    private val localElephantDataSource: LocalElephantDataSource,
    private val elephantApiService: ElephantApiService
) : ElephantRepository {
    override suspend fun getElephants(): List<Elephant> {
        refreshElephantListIfNeeded()
        return localElephantDataSource.getElephants()
    }

    override suspend fun getElephant(elephantName: String): Elephant {
        refreshElephantIfNeeded(elephantName)
        return localElephantDataSource.getElephant(elephantName)
    }

    private suspend fun refreshElephantListIfNeeded() {
        if (localElephantDataSource.hasStaleElephants()) {
            val elephantListResponse = elephantApiService.fetchElephants()
            val normalisedElephants = elephantListResponse
                .filter { it.name != null }
                .map { it.toElephant() }

            localElephantDataSource.saveElephants(normalisedElephants)
        }
    }

    private suspend fun refreshElephantIfNeeded(elephantName: String) {
        if (localElephantDataSource.hasStaleOrAbsent(elephantName)) {
            val elephantResponse = elephantApiService.fetchElephant(elephantName)
            elephantResponse?.let {
                localElephantDataSource.saveElephant(it.toElephant())
            }
        }
    }
}
