package com.example.onlinestore

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        // TODO: Change the IP address accordingly
        val IP_ADDRESS = "192.168.1.6"
        val brandsURL = "http://$IP_ADDRESS/OnlineStoreApp/fetch_brands.php"

        var brandsList = ArrayList<String>()

        // Creating a request queue in order to fetch the JSON data
        var requestQ = Volley.newRequestQueue(this@HomeScreenActivity)
        var jsonAR = JsonArrayRequest(Request.Method.GET, brandsURL, null, { response ->

            for (jsonObject in 0.until(response.length())) {
                brandsList.add(response.getJSONObject(jsonObject).getString("brand"))
            }

            var brandsListAdapter = ArrayAdapter(this@HomeScreenActivity, R.layout.brand_item_text_view, brandsList)
            brandsListView.adapter = brandsListAdapter

        }, { error ->
            // Creating an Alert Dialog
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })

        requestQ.add(jsonAR)

        // Creating onClick Listener for items in the list
        brandsListView.setOnItemClickListener { parent, view, position, id ->
            val tappedBrand = brandsList.get(position)
            val intent = Intent(this@HomeScreenActivity, FetchEProductsActivity::class.java)

            // Passing the selected brand to the next activity
            intent.putExtra("BRAND", tappedBrand)
            startActivity(intent)
        }
    }
}