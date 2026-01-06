package com.alvaropv13.apppokemon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alvaropv13.apppokemon.adapter.PokemonAdapter
import com.alvaropv13.apppokemon.viewmodel.PokemonViewModel

class FavPokemonFragment : Fragment(R.layout.fragment_fav_pokemon) {
    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        adapter = PokemonAdapter(
            emptyList(),
            onClick = { pokemon ->
                viewModel.seleccionarPokemon(pokemon)
                requireActivity().findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_favPokemonFragment_to_detailsFragment)
            },
            onFavoritoClick = { posicion ->
                viewModel.cambiarFavorito(posicion)
                viewModel.obtenerFavoritos()
            }
        )

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        viewModel.pokemons.observe(viewLifecycleOwner) { lista ->
            adapter.actualizarLista(lista)
        }

        viewModel.obtenerFavoritos()

    }
}
