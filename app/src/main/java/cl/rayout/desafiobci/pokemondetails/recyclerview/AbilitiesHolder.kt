package cl.rayout.desafiobci.pokemondetails.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.rayout.desafiobci.pokemondetails.model.AbilitiesModel
import kotlinx.android.synthetic.main.abilities_item.view.*

class AbilitiesHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(abilities: AbilitiesModel) {
        itemView.abilityName.text = abilities.name
    }
}