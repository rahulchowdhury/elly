package co.rahulchowdhury.elly.data.repo

import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.source.local.elephant.LocalElephantDataSource
import co.rahulchowdhury.elly.data.source.remote.elephant.RemoteElephantDataSource

class DefaultElephantRepository(
    private val localElephantDataSource: LocalElephantDataSource,
    private val remoteElephantDataSource: RemoteElephantDataSource
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
        println("Stale? : ${localElephantDataSource.hasStaleOrNoElephants()}")
        if (localElephantDataSource.hasStaleOrNoElephants()) {
            val elephantList = remoteElephantDataSource.fetchElephants()
            localElephantDataSource.saveElephants(elephantList)
        }
    }

    private suspend fun refreshElephantIfNeeded(elephantName: String) {
        if (localElephantDataSource.hasStaleOrNoElephant(elephantName)) {
            val elephant = remoteElephantDataSource.fetchElephant(elephantName)
            localElephantDataSource.saveElephant(elephant)
        }
    }
}
