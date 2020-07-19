package cl.rayout.desafiobci.dashboard.model

import cl.rayout.desafiobci.common.recyclerview.AdapterClick
import cl.rayout.desafiobci.common.recyclerview.RecyclerItem

data class DashboardPokemonModel(
    override val id: String,
    val name: String,
    val url: String
) : RecyclerItem, AdapterClick