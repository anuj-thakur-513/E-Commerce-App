package com.example.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fetch_eproducts.*

class FetchEProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproducts)

        val selectedBrand = intent.getStringExtra("BRAND")

        productsHeading.text = "Products of $selectedBrand"

        // List to hold the fetched products
        var productsList = ArrayList<EProduct>()


        // TODO: Change the IP address accordingly
        val IP_ADDRESS = "192.168.1.6"
        val productsURL = "http://$IP_ADDRESS/OnlineStoreApp/fetch_eproducts.php?brand=$selectedBrand"

        // creating a request queue for background thread
        val requestQ = Volley.newRequestQueue(this@FetchEProductsActivity)
        val jsonAR = JsonArrayRequest(Request.Method.GET, productsURL, null, {response->

            for (productJOIndex in 0.until(response.length())){
                productsList.add(EProduct(response.getJSONObject(productJOIndex).getInt("id"),
                    response.getJSONObject(productJOIndex).getString("name"),
                    response.getJSONObject(productJOIndex).getInt("price"),
                    response.getJSONObject(productJOIndex).getString("brand"),
                    response.getJSONObject(productJOIndex).getString("picture")))
            }



        }, {error->

            val dialogBuilder = AlertDialog.Builder(this@FetchEProductsActivity)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()

        })

    }
}