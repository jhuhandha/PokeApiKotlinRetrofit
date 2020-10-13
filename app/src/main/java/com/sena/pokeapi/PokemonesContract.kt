package com.sena.pokeapi


import android.provider.BaseColumns

class PokemonesContract {

    companion object{

        val VERSION = 1

        class Entrada:BaseColumns{
            companion object{
                val NOMBRE_TABLA = "pokemones"

                val COLUMNA_ID = "id"
                val COLUMNA_NOMBRE = "nombre"
                val COLUMNA_IMAGEN = "imagen"
                val COLUMNA_FAVORITO = "favorito"
            }
        }
    }
}