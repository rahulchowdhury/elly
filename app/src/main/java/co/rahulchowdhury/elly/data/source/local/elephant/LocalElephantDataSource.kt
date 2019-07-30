package co.rahulchowdhury.elly.data.source.local.elephant

import co.rahulchowdhury.elly.data.model.local.Elephant

interface LocalElephantDataSource {
    suspend fun getElephants(): List<Elephant>
}
