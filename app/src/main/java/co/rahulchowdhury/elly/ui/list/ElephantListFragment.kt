package co.rahulchowdhury.elly.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import co.rahulchowdhury.elly.R
import co.rahulchowdhury.elly.databinding.ElephantListFragmentBinding
import co.rahulchowdhury.elly.ui.base.BaseFragment
import kotlinx.android.synthetic.main.elephant_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ElephantListFragment : BaseFragment<ElephantListViewModel, ElephantListFragmentBinding>() {
    override val layoutId: Int = R.layout.elephant_list_fragment
    override val viewModel: ElephantListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val elephantListAdapter = ElephantListAdapter()
        elephantList?.adapter = elephantListAdapter

        subscribeToElephantListData(elephantListAdapter)

        if (savedInstanceState == null) {
            viewModel.loadElephants()
        }
    }

    private fun subscribeToElephantListData(elephantListAdapter: ElephantListAdapter) {
        viewModel.elephants.observe(viewLifecycleOwner, Observer { elephants ->
            if (elephants != null) elephantListAdapter.submitList(elephants)
        })
    }
}
