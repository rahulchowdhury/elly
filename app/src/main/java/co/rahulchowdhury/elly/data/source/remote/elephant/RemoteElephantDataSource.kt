package co.rahulchowdhury.elly.data.source.remote.elephant

import co.rahulchowdhury.elly.data.model.local.Elephant

interface RemoteElephantDataSource {
    suspend fun fetchElephants(): List<Elephant>

    suspend fun fetchElephant(elephantName: String): Elephant
}
