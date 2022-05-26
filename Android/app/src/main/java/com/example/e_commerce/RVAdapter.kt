package com.example.e_commerce

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(var context: Context, var arrayList: ArrayList<EProduct>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var productView = LayoutInflater.from(context).inflate(R.layout.rv_row, parent, false)
        return ProductViewHolder(productView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).initializeUIComponents(arrayList[position].id, arrayList[position].name,
            arrayList[position].price, arrayList[position].productPicture)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ProductViewHolder(myView: View) : RecyclerView.ViewHolder(myView) {

        var pIDTextView = myView.findViewById<TextView>(R.id.rv_row_pID)
        var pNameTextView = myView.findViewById<TextView>(R.id.rv_row_pName)
        var pPriceTextView = myView.findViewById<TextView>(R.id.rv_row_pPrice)
        var pImageView = myView.findViewById<ImageView>(R.id.rv_row_productImage)

        fun initializeUIComponents(pID: Int, pName: String, pPrice: Float, pPicture: Int) {

            pIDTextView.text = pID.toString()
            pNameTextView.text = pName
            pPriceTextView.text = pPrice.toString()
            pImageView.setImageResource(pPicture)

        }

    }
}