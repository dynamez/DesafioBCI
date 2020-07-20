package cl.rayout.desafiobci.pokemondetails.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.rayout.desafiobci.pokemondetails.model.PlacesModel
import kotlinx.android.synthetic.main.places_item.view.*

class PlacesHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(place: PlacesModel) {
        itemView.placeToFind.text = place.name
    }
}