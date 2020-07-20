package cl.rayout.desafiobci.dashboard.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.rayout.desafiobci.R
import cl.rayout.desafiobci.common.recyclerview.AdapterListener
import cl.rayout.desafiobci.common.recyclerview.Cell
import cl.rayout.desafiobci.common.recyclerview.RecyclerItem
import cl.rayout.desafiobci.dashboard.model.DashboardPokemonModel


object PokemonCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is DashboardPokemonModel
    }

    override fun type(): Int {
        return R.layout.pokemon_item
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PokemonHolder(parent.viewOf(type()))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        listener: AdapterListener?
    ) {
        if (holder is PokemonHolder && item is DashboardPokemonModel) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                listener?.listen(item, holder.itemView)
            }
        }
    }
}