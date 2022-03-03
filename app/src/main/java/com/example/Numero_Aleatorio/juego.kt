package com.example.Numero_Aleatorio

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class juego : AppCompatActivity() {
    lateinit var musicaFondo:MediaPlayer
    lateinit var sonidoRespuestaC:MediaPlayer
    lateinit var sonidoRespuestaI:MediaPlayer
    lateinit var respuestaUsuario:EditText
    lateinit var btnRespuesta:Button
    lateinit var btnVolver:Button
    var numerogenerado = 0
    var numeroUsuario = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        initUI()
        musicaFondo = MediaPlayer.create(this, R.raw.fondo)
        sonidoRespuestaC = MediaPlayer.create(this, R.raw.correcto)
        sonidoRespuestaI = MediaPlayer.create(this, R.raw.incorrecto)
        reproduceMusica()
        generaNumero()
        btnRespuesta.setOnClickListener {
            val respuesta = respuestaUsuario.text.toString()
            if(respuesta.equals("")){
                Toast.makeText(this,"Ingresa un valor" , Toast.LENGTH_LONG).show()
                sonidoIncorrecto()
            }else{
                numeroUsuario = respuesta.toInt()
                if(numeroUsuario==numerogenerado){
                    sonidoCorrecto()
                }else{
                    sonidoIncorrecto()
                }
            }
        }
        btnVolver = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            val regresar = Intent(this,MainActivity::class.java)
            startActivity(regresar)
        }


    }

    override fun onStop() {
        super.onStop()
        musicaFondo.release()
        sonidoRespuestaC.release()
        sonidoRespuestaI.release()
    }
    fun reproduceMusica(){
        musicaFondo = MediaPlayer.create(this, R.raw.fondo)
        musicaFondo.isLooping = true
        //musicaFondo.setVolume(50.0f,50.0f)
        musicaFondo.start() // no need to call prepare(); create() does that for you
    }

    fun sonidoCorrecto(){
        sonidoRespuestaC.start()
    }

    fun sonidoIncorrecto(){
        sonidoRespuestaI.start()
    }
    fun initUI(){
        btnRespuesta = findViewById(R.id.btnComprobar)
        respuestaUsuario = findViewById(R.id.etEntradaUsuario)
    }

    fun generaNumero(){
        numerogenerado = Random.nextInt(0,9)
    }
}