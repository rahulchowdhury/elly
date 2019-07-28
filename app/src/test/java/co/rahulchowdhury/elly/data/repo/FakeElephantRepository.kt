package co.rahulchowdhury.elly.data.repo

import co.rahulchowdhury.elly.data.model.local.Elephant

class FakeElephantRepository : ElephantRepository {
    private lateinit var elephants: HashMap<String, Elephant>

    override suspend fun getElephant(elephantName: String): Elephant =
        elephants[elephantName] ?: throw IllegalArgumentException("Elephant not found")

    fun addElephant(elephantName: String, elephant: Elephant) {
        elephants[elephantName] = elephant
    }
}
