package co.rahulchowdhury.elly.ui.profile

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.rahulchowdhury.elly.R

class ElephantProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ElephantProfileFragment()
    }

    private lateinit var viewModel: ElephantProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.elephant_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ElephantProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
