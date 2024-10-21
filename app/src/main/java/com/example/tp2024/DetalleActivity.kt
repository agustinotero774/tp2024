package com.example.tp2024

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp2024.api.Pelicula
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode

lateinit var toolbar : Toolbar
lateinit var ivBackdrop: ImageView
lateinit var tvResumen: TextView
lateinit var tvDetalles: TextView
lateinit var tvRating: TextView

class DetalleActivity : AppCompatActivity() {

    companion object{
        const val PELICULA_EXTRA = "DetalleActivity:pelicula"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ivBackdrop = findViewById(R.id.ivBackdrop)
        tvResumen = findViewById(R.id.tvResumen)
        tvDetalles = findViewById(R.id.tvDetalles)
        tvRating = findViewById(R.id.tvRating)

        val pelicula=intent.getParcelableExtra<Pelicula>(PELICULA_EXTRA)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (pelicula != null) {
            supportActionBar!!.title = pelicula.title
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w780/${pelicula.backdrop_path}")
                .fit() // Resize to fit the ImageView
                .into(ivBackdrop)

            tvResumen.text=pelicula.overview.toString()
            obtenerDetalles(tvDetalles,pelicula)
            val decimal = BigDecimal(pelicula.vote_average).setScale(1, RoundingMode.HALF_EVEN)
            tvRating.text= decimal.toString().toString()
        }


    }

    private fun obtenerDetalles(detalles: TextView, pelicula:Pelicula) {
        detalles.text= buildSpannedString {
            bold {append("Titulo original: ")}
            appendLine(pelicula.original_title)
            bold {append("Idioma original: ")}
            appendLine(pelicula.original_language)
            bold {append("Fecha de salida: ")}
            appendLine(pelicula.release_date)
        }
    }

}