package com.alvaropv13.apppokemon.repository

import com.alvaropv13.apppokemon.R
import com.alvaropv13.apppokemon.model.Pokemon

class PokemonRepository {

    private val listaPokemon = mutableListOf(
            Pokemon("Pikachu", R.drawable.pikachu, "Pokemon eléctrico"),
            Pokemon("Charmander", R.drawable.charmander, "Pokemon de fuego"),
            Pokemon("Charmeleon", R.drawable.charmeleon, "Evolución de Charmander"),
            Pokemon("Charizard", R.drawable.charizard, "Pokemon fuego/volador"),
            Pokemon("Bulbasaur", R.drawable.bulbasaur, "Pokemon planta"),
            Pokemon("Ivysaur", R.drawable.ivysaur, "Evolución de Bulbasaur"),
            Pokemon("Venusaur", R.drawable.venusaur, "Pokemon planta/veneno"),
            Pokemon("Squirtle", R.drawable.squirtle, "Pokemon de agua")
            )

    fun getPokemon(): List<Pokemon> {
        return listaPokemon
    }

    fun eliminarPokemon(posicion: Int) {
        listaPokemon.removeAt(posicion)
    }
}