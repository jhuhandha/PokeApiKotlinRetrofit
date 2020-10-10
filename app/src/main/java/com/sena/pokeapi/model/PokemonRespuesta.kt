package com.sena.pokeapi.model

class PokemonRespuesta {

    private var results: ArrayList<Pokemon>? = null

    fun getResults(): ArrayList<Pokemon>? {
        return results
    }

    fun setResults(results: ArrayList<Pokemon>?) {
        this.results = results
    }
}