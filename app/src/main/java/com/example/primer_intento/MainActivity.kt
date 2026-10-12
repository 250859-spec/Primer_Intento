package com.example.primer_intento

import android.app.HandoffActivityData
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
  //DECLARACION DE VARIABLES
  private lateinit var tvMensaje: TextView
  private lateinit var etxtNombre: EditText
  private lateinit var btnRegistrar: Button
  private lateinit var pbCarga: ProgressBar
  private lateinit var etxtMatricula : EditText
  private lateinit var btnLimpiar: Button

  // Crear variable contadora
  private var contadorAsistente = 0

  /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
  //Zona Oncreate
      override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContentView(R.layout.activity_main)
      setContentView(R.layout.activity_main)


      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
        insets
      }

      /* Coonectar las vistas */
      tvMensaje = findViewById(R.id.tvMensaje)
      etxtNombre = findViewById(R.id.etxtNombre)
      btnRegistrar = findViewById(R.id.btnRegistrar)
      pbCarga = findViewById(R.id.pbCarga)
      etxtMatricula= findViewById(R.id.etxtMatricula)
      btnLimpiar= findViewById(R.id.btnLimpiar)

      //Creacion del listener
      btnRegistrar.setOnClickListener { registrar() }
      btnLimpiar.setOnClickListener { limpiar() }

    }

  //Funciones}
  private fun registrar() {
    val nombre = etxtNombre.text.toString().trim()
    val matricula = etxtMatricula.text.toString().trim()


    if (nombre.isEmpty()){
      Toast.makeText(this,
        "Escribe tu nombre primero", Toast.LENGTH_SHORT).show()
      return
    } else if (nombre.length < 3){
      Toast.makeText(this, "El nombre es muy corto", Toast.LENGTH_SHORT).show()
    return
    }


   //CONTADOR SALUDOS
    contadorAsistente++

    pbCarga.visibility= View.VISIBLE
    tvMensaje.text= ""
    btnRegistrar.isEnabled = false

    Handler(Looper.getMainLooper()).postDelayed({
      pbCarga.visibility = View.GONE


      tvMensaje.text = "Hola, $nombre! BIENVENIDO Es la ${contadorAsistente}° vez que nos saludamos."
      btnRegistrar.isEnabled  = true
    }, 1500)

  }
  private fun limpiar() {
    etxtNombre.setText("")
    etxtMatricula.setText("")
    pbCarga.visibility = View.GONE
    tvMensaje.text="Escribe tu nombre para empezar"
    btnRegistrar.text="Saludar"
    btnRegistrar.isEnabled=true
    etxtNombre.requestFocus()

  }

}


