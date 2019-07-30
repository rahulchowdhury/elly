package co.rahulchowdhury.elly.ui.profile

import co.rahulchowdhury.elly.LiveDataTestUtil.getValue
import co.rahulchowdhury.elly.data.repo.FakeElephantRepository
import co.rahulchowdhury.elly.exception.base.InvalidArgumentException
import co.rahulchowdhury.elly.seed.elephant.seedElephant
import co.rahulchowdhury.elly.ui.base.ViewModelTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ElephantProfileViewModelTest : ViewModelTest() {
    private lateinit var elephantProfileViewModel: ElephantProfileViewModel
    private lateinit var fakeElephantRepository: FakeElephantRepository
    private val elephant = seedElephant

    @Before
    fun setUp() {
        fakeElephantRepository = FakeElephantRepository()
        fakeElephantRepository.addElephant(
            elephantName = elephant.name,
            elephant = elephant
        )

        elephantProfileViewModel = ElephantProfileViewModel(fakeElephantRepository)
    }

    @Test
    fun `should get profile details for a provided elephant name`() {
        elephantProfileViewModel.loadElephantProfile(elephant.name)
        val loadedElephant = getValue(elephantProfileViewModel.elephant)

        assertThat(loadedElephant.id).isEqualTo(elephant.id)
        assertThat(loadedElephant.name).isEqualTo(elephant.name)
        assertThat(loadedElephant.image).isEqualTo(elephant.image)
        assertThat(getValue(elephantProfileViewModel.networkError)).isFalse()
    }

    @Test(expected = InvalidArgumentException::class)
    fun `should throw InvalidArgumentException for empty elephant name`() {
        elephantProfileViewModel.loadElephantProfile("")
    }

    @Test
    fun `should set network error to true for no network api call`() {
        fakeElephantRepository.disableNetwork()
        elephantProfileViewModel.loadElephantProfile(elephant.name)

        assertThat(getValue(elephantProfileViewModel.networkError)).isTrue()
    }
}
