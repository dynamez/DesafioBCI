package cl.rayout.desafiobci.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import cl.rayout.desafiobci.R
import cl.rayout.desafiobci.common.recyclerview.AdapterClick
import cl.rayout.desafiobci.common.recyclerview.AdapterListener
import cl.rayout.desafiobci.dashboard.model.DashboardPokemonModel
import cl.rayout.desafiobci.dashboard.recyclerview.PokemonAdapter
import cl.rayout.desafiobci.dashboard.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DashboardFragment : Fragment(), AdapterListener {
    private val viewModel: DashboardViewModel by viewModel()
    private val listAdapter by lazy { PokemonAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        viewModel.getAllPokemon()
        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer { pokemonResponse ->
            listAdapter.setupFilter(pokemonResponse?.results?.map {
                DashboardPokemonModel("asd", it.name, it.url)
            })
        })
    }

    override fun listen(click: AdapterClick?, itemView: View?) {
        when (click) {
            is DashboardPokemonModel -> Timber.d("${click.name} Clicked")
        }
    }

    //Recycler setup and listeners
    private fun setupRecycler() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = listAdapter
        }
    }
}