package com.sena.pokeapi


class Pokemon(id:String, nombre:String, imagen:String, favorito:String) {
    var id:String? = null
    var nombre:String? = null
    var imagen:String? = null
    var favorito:String? = null

    init {
        this.id = id
        this.nombre = nombre
        this.imagen = imagen
        this.favorito = favorito
    }
}