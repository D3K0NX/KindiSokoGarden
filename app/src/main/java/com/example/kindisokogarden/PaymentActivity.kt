package com.example.kindisokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // extract/receive/retrieve that passed product data from the intent
        val name=intent.getStringExtra("product_name")
        val cost=intent.getIntExtra("product_cost",0)
        val description=intent.getStringExtra("product_description")
        val photo=intent.getStringExtra("product_photo")

        // find views textviews
        val txtName=findViewById<TextView>(R.id.txt_product_name)
        val txtCost=findViewById<TextView>(R.id.txt_product_cost)
        val txtDescription=findViewById<TextView>(R.id.txt_product_description)
        val txtPhoto = findViewById<ImageView>(R.id.txt_product_photo)

        //update textview with values passed via intent
        txtName.text=name
        txtCost.text="Ksh $cost"
        txtDescription.text=description

        val imageUrl="https://kindi.alwaysdata.net/static/images/${photo}"

        //glide
        Glide.with(txtPhoto.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(txtPhoto.)


        val edtPhone=findViewById<EditText>(R.id.edt_phone)
        val payBtn=findViewById<Button>(R.id.pay)

        //set on click listener
        payBtn.setOnClickListener {
            val api = "https://kindi/alwaysdata.net/api/make_payment"
            val phone = edtPhone.text.toString().trim()
            val data = RequestParams()

            data.put("phone",phone)
            data.put("amount",cost)

            //helper object from Api Helper Class
            val helper= ApiHelper(applicationContext)
            helper.post(api,data)
        }
    }
}