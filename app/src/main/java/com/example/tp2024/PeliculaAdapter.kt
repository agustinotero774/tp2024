package com.example.tp2024

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2024.api.Pelicula
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode

interface peliculaClickedListener{
    fun onPeliculaClicked(pelicula:Pelicula)
}

class PeliculaAdapter(var peliculas: List<Pelicula>, var context:Context,
                      private val peliculaClickedListener: peliculaClickedListener):
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>(){

        class PeliculaViewHolder(view: View): RecyclerView.ViewHolder(view) {
            var ivPoster: ImageView
            var txtPelicula: TextView
            var txtFecha: TextView
            var txtGenero: TextView
            var txtRating: TextView

            init {
                ivPoster = view.findViewById(R.id.ivPoster)
                txtPelicula = view.findViewById(R.id.tvPelicula)
                txtFecha = view.findViewById(R.id.tvFecha)
                txtGenero = view.findViewById(R.id.tvGenero)
                txtRating = view.findViewById(R.id.tvRating)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun getItemCount() = peliculas.size

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val item=peliculas.get(position)
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
            .fit() // Resize to fit the ImageView
            .into(holder.ivPoster)
        holder.txtPelicula.text=item.title
        holder.txtFecha.text=item.release_date
        holder.txtGenero.text= item.genre_ids.get(0).toString()
        val decimal = BigDecimal(item.vote_average).setScale(1, RoundingMode.HALF_EVEN)
        holder.txtRating.text= decimal.toString().toString()

        holder.itemView.setOnClickListener{ peliculaClickedListener.onPeliculaClicked(item) }
    }
}