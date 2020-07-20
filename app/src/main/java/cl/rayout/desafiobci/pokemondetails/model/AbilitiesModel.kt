package cl.rayout.desafiobci.pokemondetails.model

import cl.rayout.desafiobci.common.recyclerview.AdapterClick
import cl.rayout.desafiobci.common.recyclerview.RecyclerItem

data class AbilitiesModel(override val id: String?, val name: String) : RecyclerItem, AdapterClick