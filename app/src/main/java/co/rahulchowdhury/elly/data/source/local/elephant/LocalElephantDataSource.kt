package co.rahulchowdhury.elly.data.source.local.elephant

import co.rahulchowdhury.elly.data.model.local.Elephant

interface LocalElephantDataSource {
    suspend fun getElephants(): List<Elephant>

    suspend fun getElephant(elephantName: String): Elephant

    suspend fun hasStaleOrNoElephants(): Boolean

    suspend fun hasStaleOrNoElephant(elephantName: String): Boolean

    suspend fun saveElephant(elephant: Elephant)

    suspend fun saveElephants(elephantList: List<Elephant>)
}
