package com.example.tp2024

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    lateinit var etUsuario : EditText
    lateinit var etPassword : EditText
    lateinit var cbRecordarUsuario : CheckBox
    lateinit var btnRegistrarse : Button
    lateinit var btnIniciarSesion : Button
    lateinit var toolbar : Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Solicitar permiso de notificación en Android 13 o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 100)
            }
        }

//---------------------------------------------------------------------------



        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Login"

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)
        btnRegistrarse = findViewById(R.id.btnRegistrar)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        var preferencias= getSharedPreferences(resources.getString(R.string.sp_credenciales),
            MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        var passwordGuardada = preferencias.getString(resources.getString(R.string.passwor_usuario), "")

        if(usuarioGuardado!="" && passwordGuardada!="") {
            startMainActivity(usuarioGuardado)
        }


        btnRegistrarse.setOnClickListener {
            val intent = Intent(this,FormularioUsuarioActivity::class.java)
            startActivity(intent)
        }

        btnIniciarSesion.setOnClickListener {
            var usuario = etUsuario.text.toString()
            var contrasenia = etPassword.text.toString()
            if (contrasenia.isEmpty() || usuario.isEmpty()){
                var mensaje = "Completar Datos"
                Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
            }else{
                val usuarioDao=AppDatabase.getDatabase(this).usuarioDao()
                val usuarioBdd=usuarioDao.getUsuarioNombre(usuario)
                if(usuarioBdd!=null && usuarioBdd.password.toString().equals(contrasenia)){
                    if (cbRecordarUsuario.isChecked){
                        mostrarNotificacionRecordarUsuario()
                        var preferencias= getSharedPreferences(resources.getString(R.string.sp_credenciales),
                            MODE_PRIVATE)
                        preferencias.edit().putString(resources.getString(R.string.nombre_usuario), usuario).apply()
                        preferencias.edit().putString(resources.getString(R.string.passwor_usuario), contrasenia).apply()
                    }
                    startMainActivity(usuario)
                } else {
                    Toast.makeText(this,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show()
                }
            }



        }


    }

    //-------------------------------------------------------------
    private fun mostrarNotificacionRecordarUsuario() {
        val CHANNEL_ID = "canal_recordar_usuario"
        val NOTIFICATION_ID = 1

        // Verificar si el permiso para las notificaciones ha sido concedido (Android 13 o superior)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Si no se ha concedido el permiso, no mostrar la notificación y logear el error
            Log.e("Permisos", "Permiso de notificaciones no concedido")
            Toast.makeText(this, "Por favor, concede el permiso de notificaciones.", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear el canal de notificación (solo necesario para Android 8.0 o superior)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nombre = "Canal de Recordar Usuario"
            val descripcionText = "Canal para recordar usuario y contraseña"
            val importancia = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, nombre, importancia).apply {
                description = descripcionText
            }
            // Registrar el canal con el sistema
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Crear la notificación
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification) // Asegúrate de tener un ícono válido
            .setContentTitle("Usuario Recordado")
            .setContentText("El usuario y la contraseña han sido guardados.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Mostrar la notificación
        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }


    //-------------------------------------------------------------


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.itemPrueba){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun startMainActivity(usuarioGuardado: String?) {
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra(resources.getString(R.string.nombre_usuario), usuarioGuardado)
        startActivity(intent)
        finish()

    }

}