package com.hop

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.getbase.floatingactionbutton.FloatingActionButton
import com.hop.R.id.rv_beer_list
import com.hop.brewerydb.ui.feed.BeersActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var beerList = ArrayList<Beer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab1 = (findViewById<View>(R.id.action_add) as FloatingActionButton)
        fab1.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBeerActivity::class.java)
            startActivity(intent)
        }

        val fab2 = (findViewById<View>(R.id.action_search) as FloatingActionButton)
        fab2.setOnClickListener {
            val intent = Intent(this@MainActivity, BeersActivity::class.java)
            startActivity(intent)
        }

        rv_beer_list.layoutManager = LinearLayoutManager(this)

        loadQueryAll()
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
                val image = cursor.getBlob(cursor.getColumnIndex("Image"))

                beerList.add(Beer(id, name, date, style, brewery, image))

            } while (cursor.moveToNext())
        }

        rv_beer_list.adapter = BeerAdapter(beerList, this)
    }
}