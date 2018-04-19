package com.hop

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magicalcellar.Beer
import com.magicalcellar.DBManager
import kotlinx.android.synthetic.main.adapter_beer.view.*

class BeerAdapter(val items : ArrayList<Beer>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private var beerList = java.util.ArrayList<Beer>()

    // Gets the number of beers in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_beer, parent, false))
    }

    // Binds each beer in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tvBeerName?.text = items.get(position).beerName
        holder?.tvBrewery?.text = items.get(position).brewery
        holder?.tvCreationDate?.text = items.get(position).creationDate
        holder?.tvBeerStyle?.text = items.get(position).beerStyle
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each beer to
    val tvBeerName = view.tvBeerName
    val tvBrewery = view.tvBrewery
    val tvCreationDate = view.tvCreationDate
    val tvBeerStyle = view.tvBeerStyle
}