package com.example.onlinestore

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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

        // Logging in the user
        activity_main_btnLogin.setOnClickListener {
            val loginUrl = "http://192.168.83.48/OnlineStoreApp/login_app_user.php?email=" +
                    activity_main_edtEmail.text.toString() +
                    "&pass=" + activity_main_edtPassword.text.toString()

            // Creating a request queue using volley library
            val requestQ = Volley.newRequestQueue(this@MainActivity)
            val stringRequest = StringRequest(Request.Method.GET, loginUrl, { response->

                if (response == "The user does exist"){
                    Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()

                } else {
                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Alert")
                    dialogBuilder.setMessage(response)
                    dialogBuilder.create().show()
                }

            }, { error->
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Alert")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()
            })

            requestQ.add(stringRequest)
        }
    }
}