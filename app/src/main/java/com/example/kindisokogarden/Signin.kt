package com.example.kindisokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        find views
        val email=findViewById<EditText>(R.id.email)
        val password=findViewById<EditText>(R.id.password)
        val signinBtn=findViewById<Button>(R.id.signin_btn)

        signinBtn.setOnClickListener {
            val api="https://kindi.alwaysdata.net/api/signin"

            if (email.text.isEmpty() || password.text.isEmpty()){
                Toast.makeText(this,"Fill all the fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val data= RequestParams()

            data.put("email",email.text.toString().trim())
            data.put("password",password.text.toString().trim())

//            object instance of apihelper class
            val helper= ApiHelper(applicationContext)
            helper.post_login(api, data)
        }

    }
}