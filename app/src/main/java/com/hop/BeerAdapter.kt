package com.hop

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.adapter_beer.view.*

class BeerAdapter(private val items : ArrayList<Beer>, private val context: Context): RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

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
        holder?.tvBeerName?.text = items[position].beerName
        holder?.tvBeerIBU?.text = "IBU: " + items[position].beerIBU
        holder?.tvCreationDate?.text = items[position].creationDate
        holder?.tvBeerStyle?.text = items[position].beerStyle

        val beerImage = items[position].image
        val bitmap = BitmapFactory.decodeByteArray(beerImage, 0, beerImage.size)
        holder?.itemView!!.ivIcon.setImageBitmap(bitmap)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each beer to
        val tvBeerName = view.tvBeerName!!
        val tvBeerIBU = view.tvBeerIBU!!
        val tvCreationDate = view.tvCreationDate!!
        val tvBeerStyle = view.tvBeerStyle!!

        private val context = view.context

        init {
            view.setOnClickListener {
                val beer = items[layoutPosition]
                val intent = Intent(context, LocalBeerInfo::class.java)
                intent.putExtra("MainActId", beer.id)
                intent.putExtra("MainActName", beer.beerName)
                intent.putExtra("MainActDate", beer.creationDate)
                intent.putExtra("MainActStyle", beer.beerStyle)
                intent.putExtra("MainActIBU", beer.beerIBU)
                intent.putExtra("MainActABV", beer.beerABV)
                intent.putExtra("MainActBrewery", beer.brewery)
                intent.putExtra("MainActDescription", beer.description)
                intent.putExtra("MainActImage", beer.image)
                context.startActivity(intent)
            }
        }

        init {
            view.setOnLongClickListener { v: View ->
                showPopup(v)
                true
            }
        }

        private fun showPopup(view: View) {
            val popup: PopupMenu?
            popup = PopupMenu(context, view)
            popup.inflate(R.menu.item_menu)

            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

                when (item!!.itemId) {
                    R.id.editButton -> {
                        val beer = items[layoutPosition]
                        val intent = Intent(context, AddBeerActivity::class.java)
                        intent.putExtra("MainActId", beer.id)
                        intent.putExtra("MainActName", beer.beerName)
                        intent.putExtra("MainActDate", beer.creationDate)
                        intent.putExtra("MainActStyle", beer.beerStyle)
                        intent.putExtra("MainActIBU", beer.beerIBU)
                        intent.putExtra("MainActABV", beer.beerABV)
                        intent.putExtra("MainActBrewery", beer.brewery)
                        intent.putExtra("MainActDescription", beer.description)
                        intent.putExtra("MainActImage", beer.image)
                        context.startActivity(intent)
                    }
                    R.id.removeButton -> {
                        val dbManager = DBManager(this.context!!)
                        val selectionArgs = arrayOf(items[layoutPosition].id.toString())
                        dbManager.delete("Id=?", selectionArgs)
                        (context as MainActivity).loadQueryAll()
                    }
                }

                true
            })

            popup.show()
        }
    }
}
