package com.alvaropv13.apppokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alvaropv13.apppokemon.viewmodel.PokemonViewModel

class DetailsFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        val imagen = view.findViewById<ImageView>(R.id.ivDetalle)
        val nombre = view.findViewById<TextView>(R.id.tvNombreDetalle)
        val descripcion = view.findViewById<TextView>(R.id.tvDescripcionDetalle)

        viewModel.pokemonSeleccionado.observe(viewLifecycleOwner) { pokemon ->
            nombre.text = pokemon.nombre
            descripcion.text = pokemon.descripcion
            imagen.setImageResource(pokemon.imagen)
        }


    }
}