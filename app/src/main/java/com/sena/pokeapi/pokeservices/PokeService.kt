package com.sena.pokeapi.pokeservices

import com.sena.pokeapi.model.PokemonRespuesta
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface PokeService {

    @GET("pokemon")
    fun obtenerListaPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<PokemonRespuesta>
}