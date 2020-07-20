package cl.rayout.desafiobci.pokemondetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import cl.rayout.desafiobci.R
import cl.rayout.desafiobci.common.recyclerview.AdapterClick
import cl.rayout.desafiobci.common.recyclerview.AdapterListener
import cl.rayout.desafiobci.common.utils.StringUtils
import cl.rayout.desafiobci.databinding.PokemonDetailsFragmentBinding
import cl.rayout.desafiobci.pokemondetails.model.AbilitiesModel
import cl.rayout.desafiobci.pokemondetails.model.MovesModel
import cl.rayout.desafiobci.pokemondetails.model.PlacesModel
import cl.rayout.desafiobci.pokemondetails.recyclerview.AbilitiesAdapter
import cl.rayout.desafiobci.pokemondetails.recyclerview.MovesAdapter
import cl.rayout.desafiobci.pokemondetails.recyclerview.PlacesAdapter
import cl.rayout.desafiobci.pokemondetails.viewmodel.PokemonDetailsViewModel
import coil.api.load
import kotlinx.android.synthetic.main.pokemon_details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PokemonDetailsFragment : Fragment(), AdapterListener {

    private val viewModel: PokemonDetailsViewModel by viewModel()
    private lateinit var dataBinder: PokemonDetailsFragmentBinding
    private val placesAdapter by lazy { PlacesAdapter(this) }
    private val movesAdapter by lazy { MovesAdapter(this) }
    private val abilitiesAdapter by lazy { AbilitiesAdapter(this) }
    private val args: PokemonDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.name = args.pokeName
        viewModel.pokeId = args.pokeId
        viewModel.pokeUrl = args.pokeUrl
        val view = inflater.inflate(R.layout.pokemon_details_fragment, container, false)
        dataBinder = PokemonDetailsFragmentBinding.bind(view).apply {
            model = viewModel
        }
        dataBinder.lifecycleOwner = this.viewLifecycleOwner
        return dataBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclers()
        setupObservers()
        viewModel.getPokemonData(args.pokeId)
        pokeImg.load(
            "https://pokeres.bastionbot.org/images/pokemon/${StringUtils.getPokemonIdfromURL(
                args.pokeUrl
            )}.png"
        )
    }

    override fun listen(click: AdapterClick?, itemView: View?) {
        TODO("Not yet implemented")
    }

    private fun setupRecyclers() {
        placesToFindRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = placesAdapter
        }
        movesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movesAdapter
        }
        abilitiesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = abilitiesAdapter
        }
    }

    private fun setupObservers() {
        viewModel.pokemonMovesResponse.observe(viewLifecycleOwner, Observer { pokemonMoves ->
            movesAdapter.setupFilter(pokemonMoves?.map {
                MovesModel(id = it.pokemonMove.name, name = it.pokemonMove.name)
            })
        })
        viewModel.pokemonAbilities.observe(viewLifecycleOwner, Observer { pokemonAbilities ->
            abilitiesAdapter.setupFilter(pokemonAbilities?.map {
                AbilitiesModel(id = it.ability.name, name = it.ability.name)
            })
        })
        viewModel.pokemonSpeciesResponse.observe(viewLifecycleOwner, Observer {
            viewModel.getPokemonEvolutions(StringUtils.getPokeIdFromEvolutionChainURL(it?.evolutionChain?.url))
        })
        viewModel.pokemonResponse.observe(viewLifecycleOwner, Observer {
            val url = StringUtils.getPokeLocationFromUrl(it?.locationAreaEncounters)
            Timber.d("Locations url  $url")
            viewModel.getPokemonLocations(StringUtils.getPokeLocationFromUrl(it?.locationAreaEncounters))

        })

        viewModel.pokeLocationResponse.observe(viewLifecycleOwner, Observer { pokeLocationList ->
            placesAdapter.setupFilter(pokeLocationList?.map {
                PlacesModel(id = it.location.name, name = it.location.name)
            })
        })
    }

}