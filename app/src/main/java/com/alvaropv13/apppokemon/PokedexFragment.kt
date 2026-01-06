package com.alvaropv13.apppokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.alvaropv13.apppokemon.adapter.PokemonAdapter
import com.alvaropv13.apppokemon.viewmodel.PokemonViewModel
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView



class PokedexFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        adapter = PokemonAdapter(
            emptyList(),
            onClick = { pokemon ->
                viewModel.seleccionarPokemon(pokemon)
                findNavController().navigate(
                    R.id.action_pokedexFragment_to_detailsFragment
                )
            },
            onFavoritoClick = { posicion ->
                viewModel.cambiarFavorito(posicion)
            }
        )

        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerview.adapter = adapter

        viewModel.pokemons.observe(viewLifecycleOwner) {
            lista -> adapter.actualizarLista(lista)
        }

        val swipeHandler = object : ItemTouchHelper.SimpleCallback (
           0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean{
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.eliminarPokemon(viewHolder.adapterPosition)
            }
        }

        ItemTouchHelper(swipeHandler).attachToRecyclerView(recyclerview)

        val searchView = view.findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filtrarPokemons(newText.orEmpty())
                return true
            }
        })

    }

}