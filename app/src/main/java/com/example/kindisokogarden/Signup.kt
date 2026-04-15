package com.example.kindisokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username=findViewById<EditText>(R.id.username)
        val email=findViewById<EditText>(R.id.email)
        val password=findViewById<EditText>(R.id.password)
        val phone=findViewById<EditText>(R.id.phone)
        val signupBtn=findViewById<Button>(R.id.signup_btn)

        signupBtn.setOnClickListener {
            val api="https://kindimanu.alwaysdata.net/api/signup"

            if (email.text.isEmpty() || password.text.isEmpty()){
                Toast.makeText(this,"Fill all the fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val data= RequestParams()

            data.put("username",username.text.toString().trim())
            data.put("email",email.text.toString().trim())
            data.put("password",password.text.toString().trim())
            data.put("phone",phone.text.toString().trim())

            val helper= ApiHelper(applicationContext)
            helper.post(api,data)
        }
    }
}