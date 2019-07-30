package co.rahulchowdhury.elly.ui.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.rahulchowdhury.elly.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class ViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
}
