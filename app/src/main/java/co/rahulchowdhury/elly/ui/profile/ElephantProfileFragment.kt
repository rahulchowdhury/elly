package co.rahulchowdhury.elly.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import co.rahulchowdhury.elly.ElephantApp
import co.rahulchowdhury.elly.R
import javax.inject.Inject

class ElephantProfileFragment : Fragment() {
    @Inject
    lateinit var elephantProfileViewModelFactory: ElephantProfileViewModelFactory

    private val viewModel: ElephantProfileViewModel by viewModels(
        factoryProducer = { elephantProfileViewModelFactory }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.elephant_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val elephantApp = activity?.application as ElephantApp

//        elephantApp.appComponent
//            .plusElephantComponent()
//            .inject(this)

        viewModel.elephant.observe(this, Observer {

        })
    }
}
