package co.rahulchowdhury.elly.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import co.rahulchowdhury.elly.R

class ElephantProfileFragment : Fragment() {
    private val viewModel: ElephantProfileViewModel by viewModels(
        factoryProducer = { SavedStateViewModelFactory(this) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.elephant_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.elephant.observe(this, Observer {

        })
    }
}
