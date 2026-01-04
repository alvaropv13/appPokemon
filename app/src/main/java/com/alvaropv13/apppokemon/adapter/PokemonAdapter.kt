package com.alvaropv13.apppokemon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvaropv13.apppokemon.R
import com.alvaropv13.apppokemon.model.Pokemon

class PokemonAdapter(
    private var lista: List<Pokemon>,
    private val onClick: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.ivPokemon)
        val nombre: TextView = view.findViewById(R.id.tvNombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = lista[position]
        holder.nombre.text = pokemon.nombre
        holder.imagen.setImageResource(pokemon.imagen)
        holder.itemView.setOnClickListener {
            onClick(pokemon)
        }
    }

    override fun getItemCount(): Int = lista.size

    fun actualizarLista(nuevaLista: List<Pokemon>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}
