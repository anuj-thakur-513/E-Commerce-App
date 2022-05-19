package com.example.e_commerce

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var sharedP: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetData.setOnClickListener {
            val serverURL: String = "http://172.16.131.17/PHPTest/test_file.php"
            var requestQ: RequestQueue = Volley.newRequestQueue(this@MainActivity)
            var stringRequest = StringRequest(Request.Method.GET, serverURL,
                { response ->
                    txtHelloWorld.text = response
                }, { error ->
                    txtHelloWorld.text = error.message
                })

            requestQ.add(stringRequest)
        }
    }
}