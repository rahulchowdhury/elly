package co.rahulchowdhury.elly.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import co.rahulchowdhury.elly.R
import co.rahulchowdhury.elly.databinding.ElephantListFragmentBinding
import kotlinx.android.synthetic.main.elephant_list_fragment.*

class ElephantListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ElephantListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.elephant_list_fragment,
            container,
            false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        openProfileButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.elephantProfileFragment))
    }
}
