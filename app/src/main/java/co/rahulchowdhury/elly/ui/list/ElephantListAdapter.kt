package co.rahulchowdhury.elly.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.rahulchowdhury.elly.data.model.local.Elephant
import co.rahulchowdhury.elly.databinding.ElephantListItemBinding

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
        holder.bind(elephant)
    }

    class ViewHolder(
        private val elephantListItemBinding: ElephantListItemBinding
    ) : RecyclerView.ViewHolder(elephantListItemBinding.root) {

        fun bind(item: Elephant) {
            elephantListItemBinding.apply {
                elephant = item
                executePendingBindings()
            }
        }
    }
}

private class ElephantDiffCallback : DiffUtil.ItemCallback<Elephant>() {
    override fun areItemsTheSame(oldItem: Elephant, newItem: Elephant): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Elephant, newItem: Elephant): Boolean =
        oldItem == newItem
}
