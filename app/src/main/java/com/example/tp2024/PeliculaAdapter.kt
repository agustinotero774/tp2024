package com.example.tp2024

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class PeliculaAdapter(var peliculas:MutableList<Pelicula>, var context:Context):
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>(){

        class PeliculaViewHolder(view: View): RecyclerView.ViewHolder(view) {
            var txtPelicula: TextView
            var txtFecha: TextView
            var txtGenero: TextView
            var txtRating: TextView

            init {
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
        holder.txtPelicula.text=item.pelicula
        holder.txtFecha.text=item.fecha
        holder.txtGenero.text=item.genero
        holder.txtRating.text= item.rating.toString().toString()

    }
}