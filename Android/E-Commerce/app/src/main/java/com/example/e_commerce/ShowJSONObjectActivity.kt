package com.example.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_show_jsonobject.*

class ShowJSONObjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_jsonobject)

        btnSubmit.setOnClickListener(View.OnClickListener { view: View? ->
            val productURL = "http://192.168.179.48/ECommerceApp/present_json.php?id=" + edtProductID.text.toString()

            val requestQ: RequestQueue = Volley.newRequestQueue(this@ShowJSONObjectActivity)
            val jsonObjectRequest: JsonObjectRequest = JsonObjectRequest(Request.Method.GET, productURL,
                null,
                { response ->
                    txtProductName.text = response.getString("name")
                    txtProductPrice.text = response.getInt("price").toString()
                },
                { error ->
                    txtProductName.text = "No product found"
                    txtProductPrice.text = "No product found"
                })

            requestQ.add(jsonObjectRequest)
        })
    }
}