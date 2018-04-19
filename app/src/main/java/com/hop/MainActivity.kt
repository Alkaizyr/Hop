package com.hop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.getbase.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import com.magicalcellar.Beer
import com.magicalcellar.DBManager


class MainActivity : AppCompatActivity() {
    private var beerList = java.util.ArrayList<Beer>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        var fab = (findViewById<View>(R.id.action_add) as FloatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBeerActivity::class.java)
            startActivity(intent)
        }

//         Creates a vertical Layout Manager
        rv_beer_list.layoutManager = LinearLayoutManager(this)

        loadQueryAll()
        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

//         Access the RecyclerView Adapter and load the data into it
        //    rv_beer_list.adapter = BeerAdapter(beers, this)

    }

    override fun onResume() {
        loadQueryAll()
        super.onResume()
    }

    fun loadQueryAll() {

        val dbManager = DBManager(this)
        val cursor = dbManager.queryAll()

        // what to show in collection list
        beerList.clear()
        if (cursor.moveToFirst()) {

            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val name = cursor.getString(cursor.getColumnIndex("Name"))
                val date = cursor.getString(cursor.getColumnIndex("Date"))
                val style = cursor.getString(cursor.getColumnIndex("Style"))
                val brewery = cursor.getString(cursor.getColumnIndex("Brewery"))

                beerList.add(Beer(id, name, date, style, brewery))

            } while (cursor.moveToNext())
        }

        //val notesAdapter = NotesAdapter(activity, listNotes)
        rv_beer_list.adapter = BeerAdapter(beerList, this)
    }
}