package co.rahulchowdhury.elly.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<ViewModelType : ViewModel, DataBindingType : ViewDataBinding> : Fragment() {
    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: ViewModelType

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: DataBindingType = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )

        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}
