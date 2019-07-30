package co.rahulchowdhury.elly.data.repo

import co.rahulchowdhury.elly.data.model.local.Elephant

interface ElephantRepository {
    suspend fun getElephants(): List<Elephant>

    suspend fun getElephant(elephantName: String): Elephant
}
