package co.rahulchowdhury.elly.ui.profile

import android.os.Bundle
import android.view.View
import co.rahulchowdhury.elly.R
import co.rahulchowdhury.elly.databinding.ElephantProfileFragmentBinding
import co.rahulchowdhury.elly.ui.base.BaseFragment
import co.rahulchowdhury.elly.util.Constants
import org.koin.android.viewmodel.ext.android.viewModel

class ElephantProfileFragment : BaseFragment<ElephantProfileViewModel, ElephantProfileFragmentBinding>() {
    override val layoutId: Int = R.layout.elephant_profile_fragment
    override val viewModel: ElephantProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val elephantName = arguments?.getString(Constants.Arguments.ELEPHANT_NAME)

        if (savedInstanceState == null) {
            viewModel.loadElephantProfile(elephantName ?: "Arjuna")
        }
    }
}
