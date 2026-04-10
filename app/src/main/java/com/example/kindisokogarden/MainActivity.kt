package com.example.kindisokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //  find our views
        val signIn=findViewById<Button>(R.id.sign_in)
        signIn.setOnClickListener {
        //  intent to sign in activity
            val intent= Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }

        val signUp=findViewById<Button>(R.id.sign_up)
        signUp.setOnClickListener {
            val intent= Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }
    }
}