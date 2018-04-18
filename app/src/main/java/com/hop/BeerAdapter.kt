package com.hop

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_beer.view.*

class BeerAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

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
        holder?.tvBeerName?.text = items.get(position)
        holder?.tvBrewery?.text = items.get(position)
        holder?.tvCreationDate?.text = items.get(position)
        holder?.tvBeerStyle?.text = items.get(position)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each beer to
    val tvBeerName = view.tvBeerName
    val tvBrewery = view.tvBrewery
    val tvCreationDate = view.tvCreationDate
    val tvBeerStyle = view.tvBeerStyle
}