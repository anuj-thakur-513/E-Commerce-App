package com.example.e_commerce

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var sharedP: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
                sharedP = getSharedPreferences("addData", Context.MODE_PRIVATE)
                var myEditor = sharedP?.edit()
                myEditor?.putString("product_name", edtProduct.text.toString())
                myEditor?.apply()
                edtProduct.setText("")
                Toast.makeText(this@MainActivity, "Product added", Toast.LENGTH_SHORT).show()
        }

        btnGetProduct.setOnClickListener {
            txtGetProduct.text = sharedP?.getString("product_name", "No Product Found")
        }
    }
}