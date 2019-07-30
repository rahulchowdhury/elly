package co.rahulchowdhury.elly.ui.list

import co.rahulchowdhury.elly.LiveDataTestUtil.getValue
import co.rahulchowdhury.elly.data.repo.FakeElephantRepository
import co.rahulchowdhury.elly.seed.elephant.seedElephant
import co.rahulchowdhury.elly.ui.base.ViewModelTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ElephantListViewModelTest : ViewModelTest() {
    private lateinit var elephantListViewModel: ElephantListViewModel
    private lateinit var fakeElephantRepository: FakeElephantRepository
    private val elephant = seedElephant

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
