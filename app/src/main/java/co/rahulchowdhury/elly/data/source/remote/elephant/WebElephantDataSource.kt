package co.rahulchowdhury.elly.data.source.remote.elephant

import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.model.remote.toElephant

class WebElephantDataSource(
    private val elephantApiService: ElephantApiService
) : RemoteElephantDataSource {
    override suspend fun fetchElephants(): List<Elephant> {
        val elephantListResponse = elephantApiService.fetchElephants()

        return elephantListResponse
            .filter { it.name != null }
            .map { it.toElephant() }
    }

    override suspend fun fetchElephant(elephantName: String): Elephant {
        return elephantApiService.fetchElephant(elephantName).toElephant()
    }
}
