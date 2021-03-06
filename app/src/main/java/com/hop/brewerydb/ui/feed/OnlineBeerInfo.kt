package com.hop.brewerydb.ui.feed

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hop.AddBeerActivity
import com.hop.R
import kotlinx.android.synthetic.main.activity_online_beer_info.*

class OnlineBeerInfo : AppCompatActivity() {
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online_beer_info)

        val bundle: Bundle = intent.extras
        val mediumImage = bundle.getString("MainActImageMedium")
        val largeImage = bundle.getString("MainActImageLarge")

        beerName.text = bundle.getString("MainActName")
        beerStyle.text = bundle.getString("MainActStyle")
        beerIBU.text = bundle.getString("MainActIBU")
        beerABV.text = bundle.getString("MainActABV")
        beerDescription.text = bundle.getString("MainActDescription")

        Glide.with(this).load(if (largeImage.isNotBlank()) {
            largeImage
        } else {
            mediumImage
        }).into(beerImage)

        btAddFromBreweryDB.setOnClickListener {btAddFromBreweryDB
            val intent = Intent(this, AddBeerActivity::class.java)
            intent.putExtra("MainActName", beerName.text.toString())
            intent.putExtra("MainActStyle", beerStyle.text.toString())
            intent.putExtra("MainActIBU", beerIBU.text.toString())
            intent.putExtra("MainActABV", beerABV.text.toString())
            intent.putExtra("MainActDescription", beerDescription.text.toString())
            intent.putExtra("MainActImageMedium", mediumImage)
            intent.putExtra("MainActImageLarge", largeImage)
            this.startActivity(intent)
        }
    }
}
