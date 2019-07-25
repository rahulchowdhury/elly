package co.rahulchowdhury.elly.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import co.rahulchowdhury.elly.R
import kotlinx.android.synthetic.main.elephant_profile_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ElephantProfileFragment : Fragment() {
    private val elephantProfileViewModel: ElephantProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.elephant_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        elephantProfileViewModel.elephant.observe(viewLifecycleOwner, Observer {
            apiResponse?.text = "$it:${elephantProfileViewModel.elephant}"
        })
    }
}
