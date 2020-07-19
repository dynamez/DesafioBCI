package cl.rayout.desafiobci.dashboard.model

import androidx.room.Entity
import cl.rayout.desafiobci.common.recyclerview.AdapterClick
import cl.rayout.desafiobci.common.recyclerview.RecyclerItem

@Entity(tableName = "pokemon")
data class DashboardPokemonModel(
    override val id: String,
    val name: String,
    val url: String
) : RecyclerItem, AdapterClick