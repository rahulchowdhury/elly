package co.rahulchowdhury.elly.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import co.rahulchowdhury.elly.R
import co.rahulchowdhury.elly.databinding.ElephantProfileFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ElephantProfileFragment : Fragment() {
    private val elephantProfileViewModel: ElephantProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ElephantProfileFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.elephant_profile_fragment,
            container,
            false
        )

        binding.viewModel = elephantProfileViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}
