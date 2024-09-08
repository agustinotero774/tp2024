package com.example.tp2024

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    lateinit var etUsuario : EditText
    lateinit var etPassword : EditText
    lateinit var cbRecordarUsuario : CheckBox
    lateinit var btnRegistrarse : Button
    lateinit var btnIniciarSesion : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)
        btnRegistrarse = findViewById(R.id.btnRegistrar)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        btnRegistrarse.setOnClickListener {

            var mensaje = ""

            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()

            val intent = Intent(this,FormularioUsuarioActivity::class.java)
            startActivity(intent)





        }
        btnIniciarSesion.setOnClickListener {

        var usuario = etUsuario.text.toString()
        if (etUsuario.text.toString().isEmpty() || etPassword.text.toString().isEmpty()){
            var mensaje = "Completar Datos"
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
        }else{
            if (cbRecordarUsuario.isChecked){
                Log.i("TODO","Recordar usuario y contraseña")
            }
        val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("NOMBRE",usuario)

            startActivity(intent)
            finish()

        }

        }


    }
}