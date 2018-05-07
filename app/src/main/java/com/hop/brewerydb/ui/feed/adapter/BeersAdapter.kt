package com.hop.brewerydb.ui.feed.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hop.LocalBeerInfo
import com.hop.R
import com.hop.R.id.beerImage
import com.hop.brewerydb.model.Beer
import com.hop.brewerydb.ui.feed.OnlineBeerInfo
import kotlinx.android.synthetic.main.item_beer.view.*

class BeersAdapter : RecyclerView.Adapter<BeersAdapter.BeerHolder>() {
    private val beers = mutableListOf<Beer>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BeerHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_beer, parent, false)

        return BeerHolder(view)
    }

    override fun onBindViewHolder(holder: BeerHolder?, position: Int) {
        val beer = beers[position]

        holder?.run { showBeer(beer) }
    }

    fun clearIfNeeded(page: Int) {
        if (page == 1) {
            beers.clear()
        }
    }

    fun addItems(newBeers: List<Beer>) {
//        load only beers with pictures
//        beers.addAll(newBeers.filter { beer ->
//        beer.labels.medium.isNotBlank() || beer.labels.large.isNotBlank()
//    })
        beers.addAll(newBeers)
        notifyDataSetChanged()
    }

    override fun getItemCount() = beers.size

    inner class BeerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val beer = beers[layoutPosition]
                val mediumImage = beer.labels.medium
                val largeImage = beer.labels.large

                val intent = Intent(itemView.context, OnlineBeerInfo::class.java)
                intent.putExtra("MainActName", beer.name)
//                intent.putExtra("MainActDate", beer.creationDate)
                intent.putExtra("MainActStyle", beer.style.name)
  //              intent.putExtra("MainActBrewery", beer.labels)

                intent.putExtra("MainActImageLarge", beer.labels.large)
                intent.putExtra("MainActImageMedium", beer.labels.medium)
                itemView.context.startActivity(intent)
            }
        }

        fun showBeer(beer: Beer): Unit = with(itemView) {
            beerStyle.text = beer.style.name
            beerName.text = beer.name

            val mediumImage = beer.labels.medium
            val largeImage = beer.labels.large

            Glide.with(itemView).load(if (largeImage.isNotBlank()) {
                largeImage
            } else {
                mediumImage
            }).into(beerImage)
        }
    }
}
