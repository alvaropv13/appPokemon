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

    val pokemonSeleccionado: MutableLiveData<Pokemon> = MutableLiveData()

    fun seleccionarPokemon(pokemon: Pokemon) {
        pokemonSeleccionado.value = pokemon
    }

    fun cambiarFavorito(posicion: Int) {
        val pokemon = pokemons.value?.get(posicion) ?: return

        if (pokemon.favorito) {
            repository.desmarcarFavorito(posicion)
        } else {
            repository.marcarFavorito(posicion)
        }

        pokemons.value = repository.getPokemon()
    }

    fun obtenerFavoritos() {
        pokemons.value = repository.getFavoritos()
    }


    fun filtrarPokemons(texto: String) {
        if (texto.isEmpty()) {
            pokemons.value = repository.getPokemon()
        } else {
            pokemons.value = repository.getPokemon().filter {
                it.nombre.contains(texto, ignoreCase = true)
            }
        }
    }

}