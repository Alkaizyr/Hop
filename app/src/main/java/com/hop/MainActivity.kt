package com.hop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.getbase.floatingactionbutton.FloatingActionButton
import com.hop.brewerydb.ui.feed.BeersActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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

        val fab3 = (findViewById<View>(R.id.action_achievements) as FloatingActionButton)
        fab3.setOnClickListener {
            val intent = Intent(this@MainActivity, Achievements::class.java)
            intent.putExtra("beer_list", beerList.count())
            var stylesCount = 0
            var breweriesCount = 0
            var oldBool = false
            var fields_Filled = false
            for (beer in beerList) {
                if (beer.beerStyle != "") stylesCount++
                if (beer.brewery != "") breweriesCount++
                if ((beer.creationDate!!.length >3) and
                    (beer.creationDate!!.substring(beer.creationDate!!.length - 3).toInt() >
                            Calendar.getInstance().get(Calendar.YEAR)-9))
                    oldBool = true
                if ((beer.beerName != "") and (beer.creationDate != "") and (beer.beerIBU != "")
                        and (beer.beerABV != "") and (beer.beerStyle != "") and (beer.brewery != "")
                        and (beer.description != "")) fields_Filled = true
            }
            intent.putExtra("numberOfStyles", stylesCount)
            intent.putExtra("numberOfBreweries", breweriesCount)
            intent.putExtra("oldbool", oldBool)
            intent.putExtra("fieldsFilled", fields_Filled)
            startActivity(intent)
        }

        editTextversion?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                // after the change calling the method and passing the search input
                filter(editable.toString())
            }
        })

        rv_beer_list.layoutManager = LinearLayoutManager(this)

        loadQueryAll()

        btClearSearch.setOnClickListener {
            // Hide virtual key board when clicking
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(btClearSearch.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN)

            editTextversion.clearFocus()
            editTextversion.setText("")
        }
    }

    override fun onResume() {
        loadQueryAll()
        super.onResume()
    }

    private fun filter(text: String) {
        val filterdNames = ArrayList<Beer>()

        for (s in beerList) {
            if (s.beerName!!.toLowerCase().contains(text.toLowerCase())) {
                filterdNames.add(s)
            }
        }
        rv_beer_list.adapter = BeerAdapter(filterdNames, this)
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
                val ibu = cursor.getString(cursor.getColumnIndex("IBU"))
                val abv = cursor.getString(cursor.getColumnIndex("ABV"))
                val style = cursor.getString(cursor.getColumnIndex("Style"))
                val brewery = cursor.getString(cursor.getColumnIndex("Brewery"))
                val description = cursor.getString(cursor.getColumnIndex("Description"))
                val image = cursor.getBlob(cursor.getColumnIndex("Image"))

                beerList.add(Beer(id, name, date, ibu, abv, style, brewery, description, image))

            } while (cursor.moveToNext())
        }

        rv_beer_list.adapter = BeerAdapter(beerList, this)
    }

}
