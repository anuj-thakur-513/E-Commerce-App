package com.example.e_commerce

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_downloading_image.*

class DownloadingImageActivity : AppCompatActivity() {

    val urlImage = "http://172.16.131.17/sample_image.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloading_image)

        btnDownloadImage.setOnClickListener {
            Picasso.get().load(urlImage).into(imgDownloadedImage)
        }
    }
}