package co.rahulchowdhury.elly.ui.profile

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

class ElephantProfileViewModelTest {
    private lateinit var elephantProfileViewModel: ElephantProfileViewModel
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
        val fakeElephantRepository = FakeElephantRepository()
        fakeElephantRepository.addElephant(
            elephantName = elephant.name,
            elephant = elephant
        )

        elephantProfileViewModel = ElephantProfileViewModel(fakeElephantRepository)
    }

    @Test
    fun getElephant_gotElephant() {
        elephantProfileViewModel.loadElephantProfile(elephant.name)
        val loadedElephant = getValue(elephantProfileViewModel.elephant)

        assertThat(loadedElephant.id).isEqualTo(elephant.id)
        assertThat(loadedElephant.name).isEqualTo(elephant.name)
        assertThat(loadedElephant.image).isEqualTo(elephant.image)
    }
}
