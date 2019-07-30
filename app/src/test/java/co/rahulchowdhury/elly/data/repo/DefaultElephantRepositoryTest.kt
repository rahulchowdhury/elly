package co.rahulchowdhury.elly.data.repo

import co.rahulchowdhury.elly.data.source.local.elephant.LocalElephantDataSource
import co.rahulchowdhury.elly.data.source.remote.elephant.RemoteElephantDataSource
import co.rahulchowdhury.elly.seed.elephant.seedElephant
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DefaultElephantRepositoryTest {
    @Mock
    lateinit var fakeLocalElephantDataSource: LocalElephantDataSource

    @Mock
    lateinit var fakeRemoteElephantDataSource: RemoteElephantDataSource

    lateinit var defaultElephantRepository: DefaultElephantRepository

    @Before
    fun setUp() {
        defaultElephantRepository = DefaultElephantRepository(
            localElephantDataSource = fakeLocalElephantDataSource,
            remoteElephantDataSource = fakeRemoteElephantDataSource
        )
    }

    @Test
    fun `should get elephants from local source`() {
        runBlocking {
            `when`(fakeLocalElephantDataSource.getElephants()).thenReturn(listOf(seedElephant))
            `when`(fakeLocalElephantDataSource.hasStaleOrNoElephants()).thenReturn(false)

            val elephants = defaultElephantRepository.getElephants()

            assertThat(elephants).hasSize(1)
        }
    }
}
