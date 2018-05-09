package com.hop.brewerydb.ui.feed

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hop.R
import kotlinx.android.synthetic.main.activity_online_beer_info.*

class OnlineBeerInfo : AppCompatActivity() {
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_beer_info)

//        try {
        val bundle: Bundle = intent.extras
        val mediumImage = bundle.getString("MainActImageMedium")
        val largeImage = bundle.getString("MainActImageLarge")

        //  id = bundle.getInt("MainActId", 0)
        // if (id != 0) {
        beerName.text = bundle.getString("MainActName")
        //        tvCreationDate.text = bundle.getString("MainActDate")
        beerStyle.text = bundle.getString("MainActStyle")
        //         tvBrewery.text = bundle.getString("MainActBrewery")

        Glide.with(this).load(if (largeImage.isNotBlank()) {
            largeImage
        } else {
            mediumImage
        }).into(beerImage)

       // Glide.with(this).load(bundle.getString("MainActImage")).into(beerImage)
        //      beerImage.setImageBitmap(BitmapFactory.decodeByteArray(bundle.getString("MainActImage").toByteArray(), 0, bundle.getString("MainActImage").toByteArray().size))
        //          }
        //} catch (ex: Exception) {
        //}
    }
}
