package com.example.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_products.*

class CartProductsActivity : AppCompatActivity() {
    val IP_ADDRESS = "192.168.1.6"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)

        var cartProductsUrl = "http://$IP_ADDRESS/OnlineStoreApp/fetch_temporary_order.php?email=${Person.email}"
        var cartProductsList = ArrayList<String>()
        var requestQ = Volley.newRequestQueue(this@CartProductsActivity)
        var jsonAR = JsonArrayRequest(Request.Method.GET, cartProductsUrl, null, {response->

            for (joIndex in 0.until(response.length())){

                cartProductsList.add("${response.getJSONObject(joIndex).getString("name")} \n" +
                        "${response.getJSONObject(joIndex).getInt("price")} \n" +
                        "${response.getJSONObject(joIndex).getInt("amount")}")

            }

            var cartProductsAdapter = ArrayAdapter(this@CartProductsActivity, android.R.layout.simple_list_item_1, cartProductsList)
            cartProductsListView.adapter = cartProductsAdapter

        }, {error->

        })

        requestQ.add(jsonAR)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.continueShoppingItem){
            var intent = Intent(this@CartProductsActivity, HomeScreenActivity::class.java)
            startActivity(intent)
        } else if(item?.itemId == R.id.declineOrderItem){
            var deleteUrl = "http://$IP_ADDRESS/OnlineStoreApp/decline_order.php?email=${Person.email}"
            var requestQ = Volley.newRequestQueue(this@CartProductsActivity)
            var stringRequest = StringRequest(Request.Method.GET, deleteUrl, {response->
                var intent = Intent(this@CartProductsActivity, HomeScreenActivity::class.java)
                startActivity(intent)
            },{ error->

            })
            requestQ.add(stringRequest)
        }
        return super.onOptionsItemSelected(item)
    }
}