package com.example.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_finalize_shopping.*

class FinalizeShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalize_shopping)

        //TODO: Keep updating the IP ADDRESS accordingly
        val IP_ADDRESS = "192.168.1.6"
        var calculateTotalPriceUrl = "http://$IP_ADDRESS/OnlineStoreApp/calculate_total_price.php?" +
                "invoice_num=${intent.getStringExtra("LATEST_INVOICE_NUMBER")}"

        var requestQ = Volley.newRequestQueue(this@FinalizeShoppingActivity)
        var stringRequest = StringRequest(Request.Method.GET, calculateTotalPriceUrl, {response->

           btnPaymentProcessing.text = "Pay $$response via PayPal"

        }, {error->

        })
        requestQ.add(stringRequest)
    }
}