package co.rahulchowdhury.elly.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.rahulchowdhury.elly.R
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.databinding.ElephantListItemBinding
import co.rahulchowdhury.elly.util.Constants

class ElephantListAdapter : ListAdapter<Elephant, ElephantListAdapter.ViewHolder>(ElephantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ElephantListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val elephant = getItem(position)
        holder.bind(elephant, createClickListener(elephantName = elephant.name))
    }

    class ViewHolder(
        private val elephantListItemBinding: ElephantListItemBinding
    ) : RecyclerView.ViewHolder(elephantListItemBinding.root) {

        fun bind(item: Elephant, listener: View.OnClickListener) {
            elephantListItemBinding.apply {
                elephant = item
                clickListener = listener
                executePendingBindings()
            }
        }
    }

    private fun createClickListener(elephantName: String): View.OnClickListener {
        return View.OnClickListener {
            val navArgs = Bundle()
            navArgs.putString(Constants.Arguments.ELEPHANT_NAME, elephantName)

            it.findNavController().navigate(R.id.elephantProfileFragment, navArgs)
        }
    }
}

private class ElephantDiffCallback : DiffUtil.ItemCallback<Elephant>() {
    override fun areItemsTheSame(oldItem: Elephant, newItem: Elephant): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Elephant, newItem: Elephant): Boolean =
        oldItem == newItem
}
