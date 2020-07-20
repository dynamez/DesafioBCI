package cl.rayout.desafiobci.pokemondetails.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.rayout.desafiobci.R
import cl.rayout.desafiobci.common.recyclerview.AdapterListener
import cl.rayout.desafiobci.common.recyclerview.Cell
import cl.rayout.desafiobci.common.recyclerview.RecyclerItem
import cl.rayout.desafiobci.pokemondetails.model.AbilitiesModel

object AbilitiesCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is AbilitiesModel
    }

    override fun type(): Int {
        return R.layout.abilities_item
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return AbilitiesHolder(parent.viewOf(type()))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        listener: AdapterListener?
    ) {
        if (holder is AbilitiesHolder && item is AbilitiesModel) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                listener?.listen(item, holder.itemView)
            }
        }
    }
}