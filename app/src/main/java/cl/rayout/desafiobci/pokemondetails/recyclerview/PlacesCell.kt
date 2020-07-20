package cl.rayout.desafiobci.pokemondetails.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.rayout.desafiobci.R
import cl.rayout.desafiobci.common.recyclerview.AdapterListener
import cl.rayout.desafiobci.common.recyclerview.Cell
import cl.rayout.desafiobci.common.recyclerview.RecyclerItem
import cl.rayout.desafiobci.pokemondetails.model.PlacesModel


object PlacesCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is PlacesModel
    }

    override fun type(): Int {
        return R.layout.places_item
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PlacesHolder(parent.viewOf(type()))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        listener: AdapterListener?
    ) {
        if (holder is PlacesHolder && item is PlacesModel) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                listener?.listen(item, holder.itemView)
            }
        }
    }
}