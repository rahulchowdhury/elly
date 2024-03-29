package co.rahulchowdhury.elly.data.repo

import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.source.local.elephant.LocalElephantDataSource
import co.rahulchowdhury.elly.data.source.remote.elephant.RemoteElephantDataSource
import co.rahulchowdhury.elly.exception.base.InvalidArgumentException
import co.rahulchowdhury.elly.seed.elephant.seedElephant
import co.rahulchowdhury.elly.seed.elephant.seedElephants
import co.rahulchowdhury.elly.util.safeArgumentEq
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
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
    private val elephantList: List<Elephant> = seedElephants

    @Before
    fun setUp() {
        defaultElephantRepository = DefaultElephantRepository(
            localElephantDataSource = fakeLocalElephantDataSource,
            remoteElephantDataSource = fakeRemoteElephantDataSource
        )
    }

    @Test
    fun `should get elephants only from local source when cache is valid`() {
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

    @Test
    fun `should get elephants from remote and persist for invalid or no cache`() {
        runBlocking {
            `when`(fakeLocalElephantDataSource.getElephants()).thenReturn(elephantList)
            `when`(fakeLocalElephantDataSource.hasStaleOrNoElephants()).thenReturn(true)
            `when`(fakeRemoteElephantDataSource.fetchElephants()).thenReturn(elephantList)
            `when`(fakeLocalElephantDataSource.saveElephants(elephantList)).thenReturn(Unit)

            val elephants = defaultElephantRepository.getElephants()

            assertThat(elephants).hasSize(2)
            assertThat(elephants[0].name).isEqualTo(elephantList[0].name)
            assertThat(elephants[1].name).isEqualTo(elephantList[1].name)

            verify(fakeLocalElephantDataSource, times(1)).hasStaleOrNoElephants()
            verify(fakeRemoteElephantDataSource, times(1)).fetchElephants()
            verify(fakeLocalElephantDataSource, times(1)).saveElephants(
                safeArgumentEq(elephantList)
            )
            verify(fakeLocalElephantDataSource, times(1)).getElephants()

            verifyNoMoreInteractions(fakeLocalElephantDataSource)
            verifyNoMoreInteractions(fakeRemoteElephantDataSource)
        }
    }

    @Test
    fun `should get elephant details only from local source for valid cache`() {
        runBlocking {
            `when`(fakeLocalElephantDataSource.getElephant(elephant.name)).thenReturn(elephant)
            `when`(fakeLocalElephantDataSource.hasStaleOrNoElephant(elephant.name)).thenReturn(false)

            val elephantData = defaultElephantRepository.getElephant(elephant.name)

            assertThat(elephantData.name).isEqualTo(elephant.name)

            verify(fakeLocalElephantDataSource, times(1)).hasStaleOrNoElephant(
                safeArgumentEq(elephant.name)
            )
            verify(fakeRemoteElephantDataSource, never()).fetchElephant(ArgumentMatchers.anyString())
            verify(fakeLocalElephantDataSource, never()).saveElephant(elephant)
            verify(fakeLocalElephantDataSource, times(1)).getElephant(
                safeArgumentEq(elephant.name)
            )

            verifyNoMoreInteractions(fakeLocalElephantDataSource)
            verifyNoMoreInteractions(fakeRemoteElephantDataSource)
        }
    }

    @Test
    fun `should get elephant details from remote source and persist for invalid or no cache`() {
        runBlocking {
            `when`(fakeLocalElephantDataSource.getElephant(elephant.name)).thenReturn(elephant)
            `when`(fakeLocalElephantDataSource.hasStaleOrNoElephant(elephant.name)).thenReturn(true)
            `when`(fakeRemoteElephantDataSource.fetchElephant(elephant.name)).thenReturn(elephant)
            `when`(fakeLocalElephantDataSource.saveElephant(elephant)).thenReturn(Unit)

            val elephantData = defaultElephantRepository.getElephant(elephant.name)

            assertThat(elephantData.name).isEqualTo(elephant.name)

            verify(fakeLocalElephantDataSource, times(1)).hasStaleOrNoElephant(
                safeArgumentEq(elephant.name)
            )
            verify(fakeRemoteElephantDataSource, times(1)).fetchElephant(
                safeArgumentEq(elephant.name)
            )
            verify(fakeLocalElephantDataSource, times(1)).saveElephant(
                safeArgumentEq(elephant)
            )
            verify(fakeLocalElephantDataSource, times(1)).getElephant(
                safeArgumentEq(elephant.name)
            )

            verifyNoMoreInteractions(fakeLocalElephantDataSource)
            verifyNoMoreInteractions(fakeRemoteElephantDataSource)
        }
    }

    @Test(expected = InvalidArgumentException::class)
    fun `should throw InvalidArgumentException for empty elephant name`() {
        runBlocking {
            defaultElephantRepository.getElephant("")
        }
    }
}
