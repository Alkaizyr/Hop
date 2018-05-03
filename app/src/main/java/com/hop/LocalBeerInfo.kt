package com.hop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_local_beer_info.*
import kotlinx.android.synthetic.main.activity_main.*

class LocalBeerInfo : AppCompatActivity() {
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_beer_info)

        try {
            val bundle: Bundle = intent.extras
            id = bundle.getInt("MainActId", 0)
            if (id != 0) {
                beerName.setText(bundle.getString("MainActName"))
                tvCreationDate.setText(bundle.getString("MainActDate"))
                beerStyle.setText(bundle.getString("MainActStyle"))
                tvBrewery.setText(bundle.getString("MainActBrewery"))
            }
        } catch (ex: Exception) {
        }
    }
}
