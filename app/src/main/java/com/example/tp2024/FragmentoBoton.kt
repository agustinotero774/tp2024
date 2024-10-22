package com.example.tp2024

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentoBoton: Fragment() {
    var listener : FragmentoBotonInterfaz? = null
    lateinit var continuar : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragmento_boton, container, false)
        continuar = view.findViewById(R.id.botonFragmento)
        continuar.setOnClickListener{
            listener?.continuar()
        }
        return view
    }
}