package com.sena.pokeapi

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {

    val ID = "com.sena.pokeapi.ID"
    val NOMBRE = "com.sena.pokeapi.NOMBRE"
    val IMAGEN = "com.sena.pokeapi.IMAGEN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setTitle("Detalle")

        val text_id = intent.getStringExtra(ID)
        val text_nombre = intent.getStringExtra(NOMBRE)
        val text_imagen = intent.getStringExtra(IMAGEN)

        var textView : TextView = findViewById(R.id.textView)
        var imageView: ImageView = findViewById(R.id.imageView)
        textView.text = text_nombre

        Glide
            .with(this)
            .load(text_imagen)
            .centerCrop()
            .into(imageView);
    }
}