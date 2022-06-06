package com.example.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_thank_you.*

class ThankYouActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you)

        btnShopMore.setOnClickListener {
            var continueShoppingIntent = Intent(this@ThankYouActivity, HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}