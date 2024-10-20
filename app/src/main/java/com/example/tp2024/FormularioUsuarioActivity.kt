package com.example.tp2024

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormularioUsuarioActivity : AppCompatActivity() {
    lateinit var etCrearUsuario : EditText
    lateinit var etCrearPassword : EditText
    lateinit var etReescribirContrasenia : EditText
    lateinit var btnFinalizarRegistro : Button
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Registrarse"

        btnFinalizarRegistro = findViewById(R.id.btnFinalizarRegistro)
        etCrearUsuario = findViewById(R.id.etCrearUsuario)
        etCrearPassword = findViewById(R.id.etCrearContrasenia)
        etReescribirContrasenia = findViewById(R.id.etReescribirContrasenia)
        btnFinalizarRegistro.setOnClickListener {

            var usuario = etCrearUsuario.text.toString()
            var contrasenia = etCrearPassword.text.toString()

            if (usuario.isEmpty() || contrasenia.isEmpty()||etReescribirContrasenia.text.toString().isEmpty()){
                var mensaje = "Completar Datos"
                Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
            }else {
                if(etCrearPassword.text.toString().equals(etReescribirContrasenia.text.toString())){
                    val nuevoUsuario=Usuario(usuario,contrasenia)
                    AppDatabase.getDatabase(this).usuarioDao().insertUsuario(nuevoUsuario)

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, getString(R.string.contrasenasInvalidas),Toast.LENGTH_SHORT).show()
                }
            }


         //   Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()


        }
    }
}