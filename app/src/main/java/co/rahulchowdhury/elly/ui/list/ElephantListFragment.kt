package co.rahulchowdhury.elly.ui.list

import android.os.Bundle
import androidx.navigation.Navigation
import co.rahulchowdhury.elly.R
import co.rahulchowdhury.elly.databinding.ElephantProfileFragmentBinding
import co.rahulchowdhury.elly.ui.base.BaseFragment
import kotlinx.android.synthetic.main.elephant_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ElephantListFragment : BaseFragment<ElephantListViewModel, ElephantProfileFragmentBinding>() {
    override val layoutId: Int = R.layout.elephant_list_fragment
    override val viewModel: ElephantListViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        openProfileButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.elephantProfileFragment))
    }
}
