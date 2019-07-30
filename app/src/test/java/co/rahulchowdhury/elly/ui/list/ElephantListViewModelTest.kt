package co.rahulchowdhury.elly.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.rahulchowdhury.elly.LiveDataTestUtil.getValue
import co.rahulchowdhury.elly.MainCoroutineRule
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.data.repo.FakeElephantRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ElephantListViewModelTest {
    private lateinit var elephantListViewModel: ElephantListViewModel
    private lateinit var fakeElephantRepository: FakeElephantRepository
    private val elephant = Elephant(
        id = "1",
        name = "Elly",
        affiliation = "Happy Feet",
        lastFetchTime = 0,
        image = "elly-profile.jpg"
    )

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        fakeElephantRepository = FakeElephantRepository()
        fakeElephantRepository.addElephant(
            elephantName = elephant.name,
            elephant = elephant
        )

        elephantListViewModel = ElephantListViewModel(fakeElephantRepository)
    }

    @Test
    fun `should provide a list of elephants`() {
        elephantListViewModel.loadElephants()
        val elephants = getValue(elephantListViewModel.elephants)

        assertThat(elephants).hasSize(1)
        assertThat(elephants[0].name).isEqualTo(elephant.name)
    }

    @Test
    fun `should set network error to true for no network api call`() {
        fakeElephantRepository.disableNetwork()
        elephantListViewModel.loadElephants()

        assertThat(getValue(elephantListViewModel.networkError)).isTrue()
    }
}
