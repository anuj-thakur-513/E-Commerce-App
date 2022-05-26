package com.example.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_rvactivity.*

class RVActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvactivity)

        var myProductsList = ArrayList<EProduct>()

        myProductsList.add(EProduct(1,"iPhone", 1000f, R.drawable.iphone))
        myProductsList.add(EProduct(2,"MacBook Air", 2000f, R.drawable.macbookair))
        myProductsList.add(EProduct(3,"iPad", 850f, R.drawable.ipad))
        myProductsList.add(EProduct(4,"MacBook Pro", 2500f, R.drawable.macbookpro))
        myProductsList.add(EProduct(5,"iPod", 500f, R.drawable.ipodtouch))
        myProductsList.add(EProduct(6,"Apple Watch", 900f, R.drawable.applewatch))
        myProductsList.add(EProduct(7,"Apple TV", 3500f, R.drawable.appletv))

        var rvAdapter = RVAdapter(this@RVActivity, myProductsList)
        recyclerView.layoutManager = LinearLayoutManager(this@RVActivity)
        recyclerView.adapter = rvAdapter
    }
}