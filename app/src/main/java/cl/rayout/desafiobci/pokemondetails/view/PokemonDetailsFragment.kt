package cl.rayout.desafiobci.pokemondetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import cl.rayout.desafiobci.R
import cl.rayout.desafiobci.databinding.PokemonDetailsFragmentBinding
import cl.rayout.desafiobci.pokemondetails.viewmodel.PokemonDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailsFragment : Fragment() {

    private val viewModel: PokemonDetailsViewModel by viewModel()
    private lateinit var dataBinder: PokemonDetailsFragmentBinding
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
        viewModel.getPokemonData(args.pokeId)
    }

}