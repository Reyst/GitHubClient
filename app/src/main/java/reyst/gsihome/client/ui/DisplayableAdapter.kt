package reyst.gsihome.client.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import reyst.gsihome.client.R
import reyst.gsihome.client.databinding.ItemDisplayableBinding

class DisplayableAdapter(
    private val items: List<Displayable>,
    private val onItemClickAction: (Displayable) -> Unit
) : RecyclerView.Adapter<DisplayableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): DisplayableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemDisplayableBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_displayable,
                parent,
                false
            )

        return DisplayableViewHolder(binding)
            .apply {
                itemView.setOnClickListener { onItemClickAction(items[adapterPosition]) }
            }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DisplayableViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class DisplayableViewHolder(
    private val binding: ItemDisplayableBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Displayable) {
        binding.item = item
        binding.executePendingBindings()
    }
}