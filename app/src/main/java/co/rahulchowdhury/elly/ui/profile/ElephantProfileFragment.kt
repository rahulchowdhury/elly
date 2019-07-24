package co.rahulchowdhury.elly.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import co.rahulchowdhury.elly.R

class ElephantProfileFragment : Fragment() {
    private val viewModel: ElephantProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.elephant_profile_fragment, container, false)
    }
}
