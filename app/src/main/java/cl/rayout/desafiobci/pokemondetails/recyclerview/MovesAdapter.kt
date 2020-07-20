package cl.rayout.desafiobci.pokemondetails.recyclerview

import cl.rayout.desafiobci.common.recyclerview.AdapterListener
import cl.rayout.desafiobci.common.recyclerview.BaseListAdapter
import cl.rayout.desafiobci.pokemondetails.model.MovesModel

class MovesAdapter(listener: AdapterListener) : BaseListAdapter(
    MovesCell,
    listener = listener
) {
    private var unfilteredList: List<MovesModel>? = mutableListOf()

    fun setupFilter(list: List<MovesModel>?) {
        unfilteredList = list
        submitList(list)
    }

//    fun filter(query: String?): List<PokemonModel> {
//        val list = mutableListOf<PokemonModel>()
//        // perform the data filtering
//        if (!query.isNullOrEmpty()) {
//            list.addAll(unfilteredList?.filter {
//                it.let {
//                    it.name!!.toLowerCase(Locale.getDefault())
//                        .contains(query.toString().toLowerCase(Locale.getDefault()))
//                }
//            })
//        } else {
//           // list.addAll(unfilteredList)
//        }
//
//        submitList(list as List<PokemonModel>)
//        return list
//    }
}