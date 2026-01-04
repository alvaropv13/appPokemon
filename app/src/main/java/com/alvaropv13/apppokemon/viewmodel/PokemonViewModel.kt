package com.alvaropv13.apppokemon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alvaropv13.apppokemon.model.Pokemon
import com.alvaropv13.apppokemon.repository.PokemonRepository

class PokemonViewModel : ViewModel() {

    private val repository = PokemonRepository()

    val pokemons: MutableLiveData<List<Pokemon>> = MutableLiveData()

    init {
        pokemons.value = repository.getPokemon()
    }

    fun eliminarPokemon(posicion: Int) {
        repository.eliminarPokemon(posicion)
        pokemons.value = repository.getPokemon()
    }
}