package com.example.tp2024

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2024.api.MovieDbClient
import com.example.tp2024.api.Pelicula
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    lateinit var rvPeliculas:RecyclerView
    lateinit var peliculasAdapter:PeliculaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Peliculas"
        saludarUsuario()

        rvPeliculas=findViewById(R.id.rvPeliculas)
        peliculasAdapter= PeliculaAdapter(emptyList<Pelicula>(), this,
            object : peliculaClickedListener{
                override fun onPeliculaClicked(pelicula: Pelicula) {
                    navigateTo(pelicula)
                }
            })
        rvPeliculas.adapter=peliculasAdapter




        thread {
            val apiKey = getString(R.string.api_key)
            val peliculasPopulares = MovieDbClient.service.listaPeliculasPopulares(apiKey)
            val body = peliculasPopulares.execute().body()

            runOnUiThread {
                if (body != null) {
                    peliculasAdapter.peliculas = body.results
                    peliculasAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun navigateTo(pelicula: Pelicula) {

        val intent = Intent(this,DetalleActivity::class.java)
        intent.putExtra(DetalleActivity.PELICULA_EXTRA,pelicula)
        startActivity(intent)
    }

    private fun saludarUsuario(){
        var bundle: Bundle? = intent.extras
        if(bundle!=null){
            var usuario=bundle?.getString(resources.getString(R.string.nombre_usuario))
            Toast.makeText(this,"Bienvenido $usuario",Toast.LENGTH_SHORT).show()
        }

    }

}