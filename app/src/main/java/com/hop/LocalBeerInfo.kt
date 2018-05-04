package com.hop

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_local_beer_info.*

class LocalBeerInfo : AppCompatActivity() {
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_beer_info)

        try {
            val bundle: Bundle = intent.extras
            id = bundle.getInt("MainActId", 0)
            if (id != 0) {
                beerName.text = bundle.getString("MainActName")
                tvCreationDate.text = bundle.getString("MainActDate")
                beerStyle.text = bundle.getString("MainActStyle")
                tvBrewery.text = bundle.getString("MainActBrewery")
                beerImage.setImageBitmap(BitmapFactory.decodeByteArray(bundle.getByteArray("MainActImage"), 0, bundle.getByteArray("MainActImage").size))
            }
        } catch (ex: Exception) {
        }
    }
}
