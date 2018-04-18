package com.hop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // Initializing an empty ArrayList to be filled with beers
    val beers: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Loads beers into the ArrayList
        addBeers()

        // Creates a vertical Layout Manager
        rv_beer_list.layoutManager = LinearLayoutManager(this)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it
        rv_beer_list.adapter = BeerAdapter(beers, this)

    }

    // Adds beers to the empty animals ArrayList
    fun addBeers() {
        beers.add("8 Balls")
        beers.add("Friday Night")
    }
}