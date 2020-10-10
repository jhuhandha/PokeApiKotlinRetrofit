package com.sena.pokeapi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sena.pokeapi.DetailActivity
import com.sena.pokeapi.R
import com.sena.pokeapi.model.Pokemon

class ListaPokemonAdapter(data : ArrayList<Pokemon>) : RecyclerView.Adapter<MyViewHolder>() {

    private val dataset: ArrayList<Pokemon>

    init {
        this.dataset = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return this.dataset.size
    }

    fun adicionarListaPokemon(listaPokemon: ArrayList<Pokemon>?) {
        this.dataset.addAll(listaPokemon!!)
        notifyDataSetChanged()
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val fotoImageView:ImageView = itemView.findViewById(R.id.fotoImageView)
    private val nombreTextView:TextView = itemView.findViewById(R.id.nombreTextView)
    val ID = "com.sena.pokeapi.ID"
    val NOMBRE = "com.sena.pokeapi.NOMBRE"
    val IMAGEN = "com.sena.pokeapi.IMAGEN"

    fun bind(pokemon : Pokemon){
        nombreTextView.text = pokemon.getName()
        var numero : String = pokemon.getNumber().toString()
        var imagen : String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${numero}.png"
        Glide
            .with(itemView.context)
            .load(imagen)
            .centerCrop()
            .into(fotoImageView);

        fotoImageView.setOnClickListener{
            var intent: Intent = Intent(itemView.context, DetailActivity::class.java).apply {
                putExtra(ID, numero)
                putExtra(NOMBRE, pokemon.getName().toString())
                putExtra(IMAGEN, imagen)
            }
            itemView.context.startActivity(intent)
        }
    }
}