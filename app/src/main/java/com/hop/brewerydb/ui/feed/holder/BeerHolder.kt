package com.hop.brewerydb.ui.feed.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.hop.brewerydb.model.Beer
import kotlinx.android.synthetic.main.item_beer.view.*

class BeerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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