package cl.rayout.desafiobci.dashboard.model

import cl.rayout.desafiobci.common.recyclerview.RecyclerItem

data class PokemonModel(override val id: String?, val name: String, val url: String) : RecyclerItem