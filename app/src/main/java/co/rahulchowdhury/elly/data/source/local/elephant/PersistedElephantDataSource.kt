package co.rahulchowdhury.elly.data.source.local.elephant

import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.model.local.isStale
import co.rahulchowdhury.elly.exception.base.InvalidArgumentException
import co.rahulchowdhury.elly.util.Constants

class PersistedElephantDataSource(
    private val elephantDao: ElephantDao
) : LocalElephantDataSource {
    override suspend fun getElephants(): List<Elephant> = elephantDao.loadAll()

    override suspend fun getElephant(elephantName: String): Elephant =
        elephantDao.load(elephantName) ?: throw InvalidArgumentException("Elephant not found")

    override suspend fun hasStaleOrNoElephants(): Boolean {
        val staleTime = System.currentTimeMillis() - Constants.Time.FRESHNESS_PERIOD_IN_MILLIS
        val elephants = elephantDao.loadAll()
        val staleElephants = elephantDao.loadStaleElephants(staleTime)

        return elephants.isEmpty() || staleElephants.isNotEmpty()
    }

    override suspend fun hasStaleOrNoElephant(elephantName: String): Boolean {
        val elephant = elephantDao.load(elephantName)
        return elephant == null || elephant.isStale()
    }

    override suspend fun saveElephant(elephant: Elephant) {
        elephantDao.save(elephant)
    }

    override suspend fun saveElephants(elephantList: List<Elephant>) {
        elephantDao.saveMultiple(elephantList)
    }
}
