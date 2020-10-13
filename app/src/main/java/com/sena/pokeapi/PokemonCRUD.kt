package com.sena.pokeapi


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class PokemonCRUD(context: Context) {

    private var helper:DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun newPokemon(item:Pokemon){
        //abrir la base de datos en modo escritura
        val db: SQLiteDatabase = helper?.writableDatabase!!

        //Mapeo de las columnas con valores a insertar
        val values = ContentValues()
        values.put(PokemonesContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(PokemonesContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(PokemonesContract.Companion.Entrada.COLUMNA_IMAGEN, item.imagen)
        values.put(PokemonesContract.Companion.Entrada.COLUMNA_FAVORITO, item.favorito)

        //Insertar una nueva fila wn la tabla
        val newRolId = db.insert(PokemonesContract.Companion.Entrada.NOMBRE_TABLA, null, values)

        //Cerrar conexion
        db.close()
    }

    fun getPokemon():ArrayList<Pokemon>{

        val items:ArrayList<Pokemon> = ArrayList()

        //Abrir database en modo lectura
        val db: SQLiteDatabase = helper?.readableDatabase!!

        //Especificar columnas que quiero consultar
        val columnas = arrayOf(PokemonesContract.Companion.Entrada.COLUMNA_ID,
            PokemonesContract.Companion.Entrada.COLUMNA_NOMBRE,
            PokemonesContract.Companion.Entrada.COLUMNA_IMAGEN,
            PokemonesContract.Companion.Entrada.COLUMNA_FAVORITO)

        //Crear un cursor para recorrer la tabla
        val c:Cursor = db.query(
            PokemonesContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )

        //Hacer el recorrido del cursor en la tabla
        while (c.moveToNext()){
            items.add(
                Pokemon(
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_IMAGEN)),
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_FAVORITO))
            ))
        }

        //Cerrar database
        db.close()

        return items
    }

    //Regresa todos los pokemones favoritos
    fun getPokemonFavorito(favorito:String):ArrayList<Pokemon>{
        var items:ArrayList<Pokemon> = ArrayList()

        //Abrir database en modo lectura
        val db:SQLiteDatabase = helper?.readableDatabase!!

        //Especificar columnas que quiero consultar
        val columnas = arrayOf(PokemonesContract.Companion.Entrada.COLUMNA_ID,
            PokemonesContract.Companion.Entrada.COLUMNA_NOMBRE,
            PokemonesContract.Companion.Entrada.COLUMNA_IMAGEN,
            PokemonesContract.Companion.Entrada.COLUMNA_FAVORITO)

        //Crear un cursor para recorrer la tabla
        val c:Cursor = db.query(
            PokemonesContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            "favorito = ?",
            arrayOf(favorito),
            null,
            null,
            null
        )

        //Hacer el recorrido del cursor en la tabla
        while (c.moveToNext()){
            items.add(
                Pokemon(
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_IMAGEN)),
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_FAVORITO))
                ))
        }
        //Cerrar database
        db.close()

        return items!!
    }

    fun getPokemonNombre(Nombre:String):ArrayList<Pokemon>{
        var items:ArrayList<Pokemon> = ArrayList()

        //Abrir database en modo lectura
        val db:SQLiteDatabase = helper?.readableDatabase!!

        //Especificar columnas que quiero consultar
        val columnas = arrayOf(PokemonesContract.Companion.Entrada.COLUMNA_ID,
            PokemonesContract.Companion.Entrada.COLUMNA_NOMBRE,
            PokemonesContract.Companion.Entrada.COLUMNA_IMAGEN,
            PokemonesContract.Companion.Entrada.COLUMNA_FAVORITO)

        //Crear un cursor para recorrer la tabla
        val c:Cursor = db.query(
            PokemonesContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            "CHARINDEX( ? , nombre) > 0",
            arrayOf(Nombre),
            null,
            null,
            null
        )

        //Hacer el recorrido del cursor en la tabla
        while (c.moveToNext()){
            items.add(
                Pokemon(
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_IMAGEN)),
                    c.getString(c.getColumnIndexOrThrow(PokemonesContract.Companion.Entrada.COLUMNA_FAVORITO))
                ))
        }
        //Cerrar database
        db.close()

        return items!!
    }
    fun updatePokemon(item:Pokemon){
        //Abrir database en modo escritura
        val db:SQLiteDatabase = helper?.writableDatabase!!

        //Mapeo de las columnas con valores a insertar
        val values = ContentValues()
        values.put(PokemonesContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(PokemonesContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(PokemonesContract.Companion.Entrada.COLUMNA_IMAGEN, item.imagen)
        values.put(PokemonesContract.Companion.Entrada.COLUMNA_FAVORITO, item.favorito)

        db.update(
            PokemonesContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            "id = ?",
            arrayOf(item.id)
        )

        db.close()
    }

    fun deletePokemon(item:Pokemon){
        //Abrir database en modo escritura
        val db:SQLiteDatabase = helper?.writableDatabase!!

        db.delete(
            PokemonesContract.Companion.Entrada.NOMBRE_TABLA,
            "id = ?",
            arrayOf(item.id)
        )

        db.close()
    }
}