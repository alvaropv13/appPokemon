package com.alvaropv13.apppokemon

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.alvaropv13.apppokemon.adapter.PokemonAdapter
import com.alvaropv13.apppokemon.viewmodel.PokemonViewModel

class FavPokemonFragment : Fragment(R.layout.fragment_fav_pokemon) {
    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar ViewModel compartido
        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // Configuración del adapter de RecyclerView
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

        // Gestos para eliminar pokémon
        val swipeHandler = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    viewModel.eliminarPokemon(pos)
                    viewModel.obtenerFavoritos()
                }
            }
        }

        ItemTouchHelper(swipeHandler).attachToRecyclerView(recyclerView)

        viewModel.pokemons.observe(viewLifecycleOwner) { lista ->
            adapter.actualizarLista(lista)
        }

        // Barra de búsqueda para pokémons de favoritos
        val searchView = view.findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filtrarFavoritos(newText.orEmpty())
                return true
            }
        })

        viewModel.obtenerFavoritos()

    }
}
