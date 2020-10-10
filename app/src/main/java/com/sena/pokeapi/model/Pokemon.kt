package com.sena.pokeapi.model

import java.lang.Exception

class Pokemon {

    private var number = 0
    private var name: String? = null
    private var url: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getNumber(): Int {
        try {
            val urlPartes: List<String> = url!!.split("/");
            return urlPartes[urlPartes.size - 2].toInt()
        }catch (e: Exception){
            return 0;
        }
    }

    fun setNumber(number: Int) {
        this.number = number
    }
}