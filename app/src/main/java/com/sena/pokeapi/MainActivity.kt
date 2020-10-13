package com.sena.pokeapi

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sena.pokeapi.adapter.ListaPokemonAdapter
import com.sena.pokeapi.model.Pokemon
import com.sena.pokeapi.model.PokemonRespuesta
import com.sena.pokeapi.pokeservices.PokeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var retrofit: Retrofit? = null
    private var offset = 0
    private var aptoParaCargar = false
    private lateinit var listaPokemonAdapter: ListaPokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data : ArrayList<Pokemon> = ArrayList()

        val btnBuscar = findViewById<ImageButton>(R.id.ibBuscar)

        btnBuscar.setOnClickListener{
            var intent: Intent = Intent(this, BuscarActivity::class.java).apply {}
            startActivity(intent)
        }

        val layoutManager = GridLayoutManager(this, 3)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        listaPokemonAdapter = ListaPokemonAdapter(data)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = listaPokemonAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (aptoParaCargar) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            aptoParaCargar = false
                            offset += 20
                            obtenerDatos(offset)
                        }
                    }
                }
            }
        })

        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        aptoParaCargar = true
        obtenerDatos(offset)
    }

    fun obtenerDatos(offset: Int) {
        val service: PokeService = retrofit!!.create(PokeService::class.java)
        val pokemonRespuestaCall: Call<PokemonRespuesta> = service.obtenerListaPokemon(20, offset)
        pokemonRespuestaCall.enqueue(object : Callback<PokemonRespuesta?> {
            override fun onResponse(
                call: Call<PokemonRespuesta?>?,
                response: Response<PokemonRespuesta?>
            ) {
                aptoParaCargar = true
                if (response.isSuccessful()) {
                    val pokemonRespuesta: PokemonRespuesta? = response?.body()
                    val listaPokemon = pokemonRespuesta?.getResults()
                    listaPokemonAdapter.adicionarListaPokemon(listaPokemon)
                } else {

                }
            }

            override fun onFailure(call: Call<PokemonRespuesta?>?, t: Throwable) {
                aptoParaCargar = true
            }
        })
    }

}