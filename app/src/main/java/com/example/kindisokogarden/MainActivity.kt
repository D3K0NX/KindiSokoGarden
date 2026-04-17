package com.example.kindisokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

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

        val aboutInfo=findViewById<Button>(R.id.about)
        aboutInfo.setOnClickListener {
            val intent= Intent(applicationContext, AboutActivity::class.java)
            startActivity(intent)
        }

        //find the views
        val progressBar=findViewById<ProgressBar>(R.id.progressbar)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)

        val url="https://kindi.alwaysdata.net/api/get_product_details"

        //make the helper object
        val helper= ApiHelper(applicationContext)

        //use the object to access loadproducts

        helper.loadProducts(url,recyclerView,progressBar)

    }
}