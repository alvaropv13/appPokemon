package com.alvaropv13.apppokemon.repository

import com.alvaropv13.apppokemon.model.Pokemon

class PokemonRepository {

    private val listaPokemon = mutableListOf<Pokemon>()

    fun getPokemon(): List<Pokemon> {
        return listaPokemon
    }
}