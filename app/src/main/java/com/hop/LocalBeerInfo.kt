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
                beerDate.text = bundle.getString("MainActDate")
                beerStyle.text = bundle.getString("MainActStyle")
                beerBrewery.text = bundle.getString("MainActBrewery")
                beerIBU.text = bundle.getString("MainActIBU")
                beerABV.text = bundle.getString("MainActABV")
                beerDescription.text = bundle.getString("MainActDescription")
                beerImage.setImageBitmap(BitmapFactory.decodeByteArray(bundle.getByteArray("MainActImage"), 0, bundle.getByteArray("MainActImage").size))
            }
        } catch (ex: Exception) {
        }
    }
}
