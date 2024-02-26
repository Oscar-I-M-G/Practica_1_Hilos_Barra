package com.example.hilos_barra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var etiqueta : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etiqueta= findViewById(R.id.textView)
        val barraProgreso : ProgressBar = findViewById(R.id.progressBar)
        val boton : Button = findViewById(R.id.button)

        /* HANDLER
        val handler = Handler(Looper.getMainLooper())

        boton.setOnClickListener {
            val miHilo = Thread{
                Thread.sleep(2000)
                handler.post {
                    etiqueta.text = "Cambio desde el Hilo con Handler"
                }
            }
            miHilo.start()
        }

         */

        /* Con View.Post
        boton.setOnClickListener {
            val miHilo = Thread{
                Thread.sleep(2000)
                etiqueta.post {
                    etiqueta.text = "Cambio desde el Hilo con View.post"
                }
            }
            miHilo.start()
        }

         */

        /*
        boton.setOnClickListener {
           lifecycleScope.launch(Dispatchers.Main){
               segundoPlano()
           }
        }
        */

        boton.setOnClickListener {
            val ejecutor = Executors.newSingleThreadExecutor()
            ejecutor.execute {
                segundoPlano()
            }
        }

    }
    /*
    suspend fun segundoPlano() {
        Thread.sleep(2000)
        withContext(Dispatchers.Main){
            etiqueta.text = "Cambio desde Corrutinas"
        }
    }

     */

    private fun segundoPlano() {
        Thread.sleep(2000)
        runOnUiThread{
            etiqueta.text = "Cambio desde Executors"
        }
    }
}