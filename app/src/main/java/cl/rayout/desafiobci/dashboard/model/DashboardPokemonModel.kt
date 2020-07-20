package cl.rayout.desafiobci.dashboard.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import cl.rayout.desafiobci.common.recyclerview.AdapterClick
import cl.rayout.desafiobci.common.recyclerview.RecyclerItem
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "pokemon")
data class DashboardPokemonModel(
    @PrimaryKey override val id: String,
    val name: String,
    val url: String
) : RecyclerItem, AdapterClick, Parcelable