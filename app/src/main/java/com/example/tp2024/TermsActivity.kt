package com.example.tp2024

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TermsActivity : AppCompatActivity(), FragmentoBotonInterfaz {
    lateinit var toolbar : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_terms)

        toolbar = findViewById(R.id.toolbarTerms)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Terminos y condiciones"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragmentoScroll= FragmentoTexto()
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragmentoTexto,fragmentoScroll)
            .addToBackStack(null)
            .commit()

        val fragmentoBoton = supportFragmentManager
            .findFragmentById(R.id.contenedorFragmentoBoton) as? FragmentoBoton
        fragmentoBoton?.listener = this


    }



    override fun continuar() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}