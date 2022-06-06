package com.example.onlinestore

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_finalize_shopping.*
import java.math.BigDecimal

class FinalizeShoppingActivity : AppCompatActivity() {

    var ttPrice:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalize_shopping)

        //TODO: Keep updating the IP ADDRESS accordingly
        val IP_ADDRESS = "192.168.1.6"
        val calculateTotalPriceUrl =
            "http://$IP_ADDRESS/OnlineStoreApp/calculate_total_price.php?" +
                    "invoice_num=${intent.getStringExtra("LATEST_INVOICE_NUMBER")}"

        val requestQ = Volley.newRequestQueue(this@FinalizeShoppingActivity)
        val stringRequest = StringRequest(Request.Method.GET, calculateTotalPriceUrl, { response ->

            btnPaymentProcessing.text = "Pay $$response via PayPal"
            ttPrice = response.toLong()

        }, { error ->

        })
        requestQ.add(stringRequest)

        // Setting up PayPal
        val paypalConfig: PayPalConfiguration = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(MyPayPal.clientID)

        val ppService = Intent(this@FinalizeShoppingActivity, PayPalService::class.java)
        ppService.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig)
        startService(ppService)

        btnPaymentProcessing.setOnClickListener {
            var ppProcessing = PayPalPayment(BigDecimal.valueOf(ttPrice),
                "USD", "Online Store",
                PayPalPayment.PAYMENT_INTENT_SALE)

            var paypalPaymentIntent = Intent(this@FinalizeShoppingActivity, PaymentActivity::class.java)
            paypalPaymentIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig)
            paypalPaymentIntent.putExtra(PaymentActivity.EXTRA_PAYMENT, ppProcessing)
            startActivityForResult(paypalPaymentIntent, 1000)
        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == 1000 && resultCode == Activity.RESULT_OK){
                var thankYouIntent = Intent(this@FinalizeShoppingActivity, ThankYouActivity::class.java)
                startActivity(thankYouIntent)
            } else {
                Toast.makeText(this@FinalizeShoppingActivity, "Something went wrong. Try Again!", Toast.LENGTH_SHORT).show()
            }
        }

        fun onDestroy() {
            super.onDestroy()
            stopService(Intent(this, PayPalService::class.java))
        }
    }
}