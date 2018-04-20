package com.hop.brewerydb.ui.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hop.R
import com.hop.brewerydb.model.Beer
import com.hop.brewerydb.ui.feed.holder.BeerHolder

class BeersAdapter : RecyclerView.Adapter<BeerHolder>() {
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
}