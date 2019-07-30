package co.rahulchowdhury.elly.data.repo

import co.rahulchowdhury.elly.data.model.local.Elephant
import java.net.UnknownHostException

class FakeElephantRepository : ElephantRepository {
    private val elephants = mutableMapOf<String, Elephant>()
    private var networkAccess = true

    override suspend fun getElephants(): List<Elephant> {
        return ArrayList(elephants.values)
    }

    override suspend fun getElephant(elephantName: String): Elephant {
        if (!networkAccess) {
            throw UnknownHostException()
        }

        return elephants[elephantName] ?: throw IllegalArgumentException("Elephant not found")
    }

    fun addElephant(elephantName: String, elephant: Elephant) {
        elephants[elephantName] = elephant
    }

    fun disableNetwork() {
        networkAccess = false
    }
}
