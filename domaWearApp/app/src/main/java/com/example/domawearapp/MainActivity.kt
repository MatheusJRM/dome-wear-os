package com.example.domawearapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var mediaPlayer: MediaPlayer
    private val handler = Handler(Looper.getMainLooper())
    private var ttsInitialized = false

    private val welcomeMessage = "Bem-vindo ao DomaWear. Este é um aplicativo acessível para funcionários com deficiência visual. Ele indica onde ficam os setores da empresa como a recepção, cafeteria e sala de reuniões. Ele também pode indicar alertas de emergências quando detectadas. Toque no botão ao centro da tela para ouvir as instruções sobre os setores."
    private val instructionText = """
        A 10 passos está a cafeteria.
        À sua direita está a sala de reuniões.
        A recepção está atrás de você.
        O banheiro fica à sua esquerda.
        O refeitório está no fim do corredor.
    """.trimIndent()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)
        mediaPlayer = MediaPlayer.create(this, R.raw.alert)

        val textView = findViewById<TextView>(R.id.instruction_text)
        val speakButton = findViewById<Button>(R.id.speak_button)

        textView.text = "Toque no botão abaixo para ouvir as instruções sobre os setores."

        speakButton.contentDescription = "Botão para ouvir instruções sobre os setores da empresa"

        speakButton.setOnClickListener {
            if (ttsInitialized) {
                tts.speak(instructionText, TextToSpeech.QUEUE_FLUSH, null, "")
            }
        }

        startEmergencyAlerts()
    }

    private fun startEmergencyAlerts() {
        val delay = Random.nextLong(45_000L, 60_000L) // entre 20 e 45 segundos
        handler.postDelayed({
            triggerEmergency()
            startEmergencyAlerts() // agendar o próximo
        }, delay)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun triggerEmergency() {
        val message = "Atenção! Situação de emergência detectada. Mantenha a calma e aguarde instruções."

        if (ttsInitialized) {
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
        }

        mediaPlayer.start()

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("🚨 Emergência")
            .setMessage(message)
            .setCancelable(true)
            .create()

        alertDialog.show()

        // Fecha o alerta ao tocar em qualquer parte da tela
        val root = alertDialog.window?.decorView
        root?.setOnTouchListener { _, _ ->
            if (ttsInitialized) tts.stop()
            if (mediaPlayer.isPlaying) mediaPlayer.pause()
            alertDialog.dismiss()
            true
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale("pt", "BR"))
            ttsInitialized = result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED

            if (ttsInitialized) {
                tts.speak(welcomeMessage, TextToSpeech.QUEUE_FLUSH, null, "")
            }
        }
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        tts.stop()
        tts.shutdown()
        mediaPlayer.release()
        super.onDestroy()
    }
}
