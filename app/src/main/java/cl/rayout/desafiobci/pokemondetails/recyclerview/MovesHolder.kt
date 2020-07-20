package cl.rayout.desafiobci.pokemondetails.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.rayout.desafiobci.pokemondetails.model.MovesModel
import kotlinx.android.synthetic.main.moves_item.view.*

class MovesHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(moves: MovesModel) {
        itemView.moveName.text = moves.name
    }
}