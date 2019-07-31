package co.rahulchowdhury.elly.data.repo

import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.source.local.elephant.LocalElephantDataSource
import co.rahulchowdhury.elly.data.source.remote.elephant.RemoteElephantDataSource
import co.rahulchowdhury.elly.seed.elephant.seedElephant
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DefaultElephantRepositoryTest {
    @Mock
    lateinit var fakeLocalElephantDataSource: LocalElephantDataSource
    @Mock
    lateinit var fakeRemoteElephantDataSource: RemoteElephantDataSource

    lateinit var defaultElephantRepository: DefaultElephantRepository
    private val elephant: Elephant = seedElephant

    @Before
    fun setUp() {
        defaultElephantRepository = DefaultElephantRepository(
            localElephantDataSource = fakeLocalElephantDataSource,
            remoteElephantDataSource = fakeRemoteElephantDataSource
        )
    }

    @Test
    fun `should get elephants only from local source`() {
        runBlocking {
            `when`(fakeLocalElephantDataSource.getElephants()).thenReturn(listOf(elephant))
            `when`(fakeLocalElephantDataSource.hasStaleOrNoElephants()).thenReturn(false)

            val elephants = defaultElephantRepository.getElephants()

            assertThat(elephants).hasSize(1)
            assertThat(elephants[0].name).isEqualTo(elephant.name)

            verify(fakeLocalElephantDataSource, times(1)).hasStaleOrNoElephants()
            verify(fakeRemoteElephantDataSource, never()).fetchElephants()
            verify(fakeLocalElephantDataSource, never()).saveElephants(listOf())
            verify(fakeLocalElephantDataSource, times(1)).getElephants()

            verifyNoMoreInteractions(fakeLocalElephantDataSource)
            verifyNoMoreInteractions(fakeRemoteElephantDataSource)
        }
    }
}
