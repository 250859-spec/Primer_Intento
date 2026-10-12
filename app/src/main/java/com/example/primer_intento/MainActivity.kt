
package com.example.primer_intento

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

    // DECLARACION DE VARIABLES
    private lateinit var tvMensaje: TextView
    private lateinit var etxtNombre: EditText
    private lateinit var btnSaludar: Button
    private lateinit var pbCarga: ProgressBar
    private lateinit var etxtApellido: EditText
    private lateinit var btnLimpiar: Button

    // VARIABLE CONTADORA
    private var contadorAsistente = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // CONECTAR LAS VISTAS
        tvMensaje = findViewById(R.id.tvMensaje)
        etxtNombre = findViewById(R.id.etxtNombre)
        btnSaludar = findViewById(R.id.btnSaludar)
        pbCarga = findViewById(R.id.pbCarga)
        etxtApellido = findViewById(R.id.etxtApellido)
        btnLimpiar = findViewById(R.id.btnLimpiar)

        // CREACION DE LISTENERS
        btnSaludar.setOnClickListener { registrar() }
        btnLimpiar.setOnClickListener { limpiar() }
    }

    private fun registrar() {
        val nombre = etxtNombre.text.toString().trim()
        val matricula = etxtApellido.text.toString().trim()

        if (nombre.isEmpty()) {
            Toast.makeText(
                this,
                "Escribe tu nombre primero",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else if (nombre.length < 3) {
            Toast.makeText(
                this,
                "El nombre es muy corto",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // CONTADOR DE SALUDOS
        contadorAsistente++

        pbCarga.visibility = View.VISIBLE
        tvMensaje.text = ""
        btnSaludar.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            pbCarga.visibility = View.GONE

            tvMensaje.text =
                "Hola, $nombre! BIENVENIDO. Es la ${contadorAsistente}° vez que nos saludamos."

            btnSaludar.isEnabled = true
        }, 1500)
    }

    private fun limpiar() {
        etxtNombre.setText("")
        etxtApellido.setText("")
        pbCarga.visibility = View.GONE
        tvMensaje.text = "Escribe tu nombre para empezar"
        btnSaludar.text = "Saludar"
        btnSaludar.isEnabled = true
        etxtNombre.requestFocus()
    }
}



