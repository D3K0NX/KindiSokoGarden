package com.example.kindisokogarden

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class AboutActivity : AppCompatActivity() {
    lateinit var tts: TextToSpeech
    //variable for speech text
    lateinit var speechText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView=findViewById<TextView>(R.id.tv_about)
        val speakButton=findViewById<Button>(R.id.speak_btn)


        //views for speech to text
        speechText=findViewById<TextView>(R.id.tv_speech)
        val speechButton=findViewById<Button>(R.id.tap_speak_btn)

        //
        speechButton.setOnClickListener {
            //start speaking
            speakNow()
        }

        // create tts object
        tts= TextToSpeech(this){
            //check if the tts is available
            if (it== TextToSpeech.SUCCESS){
                //set up the languages
                tts.language= Locale.US

            }
        }

        speakButton.setOnClickListener {
            //extracts the text inside the about textview
            val text=textView.text.toString()
            //ask tts object to speak the text that has been passed to it
            tts.speak(text, TextToSpeech.QUEUE_FLUSH,null,null)
        }



    }

    //this function is executed when the activity/app is closed/destroyed/killed
    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }


    //
    private fun speakNow() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...")

        try {
            startActivityForResult(intent, 100)
        } catch (e: Exception) {
            Toast.makeText(this, "Speech recognition not supported", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            speechText.text = result?.get(0) ?: "Couldn't recognize speech"
        }
    }
}