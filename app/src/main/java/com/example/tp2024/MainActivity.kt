package com.example.tp2024

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

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

        rvPeliculas=findViewById(R.id.rvPeliculas)
        peliculasAdapter= PeliculaAdapter(getPeliculas(), this)
        rvPeliculas.adapter=peliculasAdapter
    }

    private fun getPeliculas(): MutableList<Pelicula> {
        var peliculas: MutableList<Pelicula> = ArrayList()

        peliculas.add(Pelicula(1, "Cadena Perpetua", "1995-03-23","Drama", 9.3))
        peliculas.add(Pelicula(2, "El padrino", "1972-09-20","Crimen",9.2))
        peliculas.add(Pelicula(3, "El caballero oscuro", "2008-07-17","Accion",9.0))
        peliculas.add(Pelicula(4, "El padrino 2", "1974-12-26","Crimen", 9.0))

        return peliculas
    }


}