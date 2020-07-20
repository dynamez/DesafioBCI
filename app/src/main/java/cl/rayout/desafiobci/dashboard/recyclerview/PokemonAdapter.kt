package cl.rayout.desafiobci.dashboard.recyclerview

import cl.rayout.desafiobci.common.recyclerview.AdapterListener
import cl.rayout.desafiobci.common.recyclerview.BaseListAdapter
import cl.rayout.desafiobci.dashboard.model.DashboardPokemonModel
import java.util.*


class PokemonAdapter(listener: AdapterListener) : BaseListAdapter(
    PokemonCell,
    listener = listener
) {
    private var unfilteredList: List<DashboardPokemonModel> = mutableListOf()

    fun setupFilter(list: List<DashboardPokemonModel>) {
        unfilteredList = list
        submitList(list)
    }

    fun filter(query: String?): List<DashboardPokemonModel> {
        val list = mutableListOf<DashboardPokemonModel>()
        // perform the data filtering
        if (!query.isNullOrEmpty()) {
            list.addAll(unfilteredList.filter {
                it.let {
                    it.name.toLowerCase(Locale.getDefault())
                        .contains(query.toString().toLowerCase(Locale.getDefault()))
                }
            })
        } else {
            list.addAll(unfilteredList)
        }

        submitList(list as List<DashboardPokemonModel>)
        return list
    }
}