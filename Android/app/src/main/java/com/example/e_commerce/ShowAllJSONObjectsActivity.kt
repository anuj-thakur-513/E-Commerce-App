package com.example.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_show_all_jsonobjects.*

class ShowAllJSONObjectsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_jsonobjects)

        txtAllProducts.setOnClickListener {
            val productsURL = "http://192.168.179.48/ECommerceApp/present_json_array.php"
            var allProducts: String = ""
            val requestQ: RequestQueue = Volley.newRequestQueue(this@ShowAllJSONObjectsActivity)
            val jsonArrayRequest: JsonArrayRequest = JsonArrayRequest(Request.Method.GET, productsURL, null,
                { response ->
                    for (productIndex in 0.until(response.length())){
                        allProducts += response.getJSONObject(productIndex).getString("name") +
                                " - " + response.getJSONObject(productIndex).getInt("price")
                    }
                    txtAllProducts.text = allProducts
            },
                {
                    error ->
                        txtAllProducts.text = error.message
                })

            requestQ.add(jsonArrayRequest)
        }
    }
}