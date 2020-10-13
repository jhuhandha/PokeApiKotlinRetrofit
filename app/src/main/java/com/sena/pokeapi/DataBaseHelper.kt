package com.sena.pokeapi


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, PokemonesContract.Companion.Entrada.NOMBRE_TABLA, null, PokemonesContract.Companion.VERSION) {

    companion object{
        val CREATE_POKEMONES_TABLA = "CREATE TABLE "+ PokemonesContract.Companion.Entrada.NOMBRE_TABLA +
                " (" + PokemonesContract.Companion.Entrada.COLUMNA_ID + " TEXT PRIMARY KEY, " +
                PokemonesContract.Companion.Entrada.COLUMNA_NOMBRE + " TEXT," +
                PokemonesContract.Companion.Entrada.COLUMNA_IMAGEN + " TEXT," +
                PokemonesContract.Companion.Entrada.COLUMNA_FAVORITO + " TEXT )"

        val REMOVE_POKEMONES_TABLA = "DROP TABLE IF EXISTS " + PokemonesContract.Companion.Entrada.NOMBRE_TABLA
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_POKEMONES_TABLA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(REMOVE_POKEMONES_TABLA)
        onCreate(db)
    }

}