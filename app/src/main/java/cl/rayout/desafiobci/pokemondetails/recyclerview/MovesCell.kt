package cl.rayout.desafiobci.pokemondetails.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.rayout.desafiobci.R
import cl.rayout.desafiobci.common.recyclerview.AdapterListener
import cl.rayout.desafiobci.common.recyclerview.Cell
import cl.rayout.desafiobci.common.recyclerview.RecyclerItem
import cl.rayout.desafiobci.pokemondetails.model.MovesModel

object MovesCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is MovesModel
    }

    override fun type(): Int {
        return R.layout.moves_item
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MovesHolder(parent.viewOf(type()))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        listener: AdapterListener?
    ) {
        if (holder is MovesHolder && item is MovesModel) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                listener?.listen(item, holder.itemView)
            }
        }
    }
}