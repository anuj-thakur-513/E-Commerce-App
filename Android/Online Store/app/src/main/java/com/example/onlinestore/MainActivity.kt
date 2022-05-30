package com.example.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Changing activity when clicked on sign up
        activity_main_btnSignUp.setOnClickListener {
            var signUpIntent = Intent(this@MainActivity, SignUpActivity::class.java);
            startActivity(signUpIntent)
        }
    }
}