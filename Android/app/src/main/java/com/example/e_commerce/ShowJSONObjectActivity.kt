package com.example.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_show_jsonobject.*

class ShowJSONObjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_jsonobject)

        btnSubmit.setOnClickListener(View.OnClickListener { view: View? ->
            
        })
    }
}