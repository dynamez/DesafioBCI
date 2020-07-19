package cl.rayout.desafiobci.dashboard.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.rayout.desafiobci.dashboard.model.DashboardPokemonModel
import coil.api.load
import kotlinx.android.synthetic.main.pokemon_item.view.*


class PokemonHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(pokemon: DashboardPokemonModel) {
        itemView.pokemonName.text = pokemon.name
        itemView.pokemonImg.load(
            "https://pokeres.bastionbot.org/images/pokemon/${pokemon.url.substring(
                33
            ).dropLast(1)}.png"
        )
    }
}