package com.hop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class SearchActivity : AppCompatActivity() {


    internal var recyclerView: RecyclerView?=null
    internal var editTextversion: EditText?=null
    internal var version: ArrayList<String> = ArrayList<String>()

    var adapter: CustomAdapter?=null

    // Search Functionality in RecyclerView using EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        editTextversion?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
            }
        })

        version = ArrayList<String>()

        version.add("KitKat")
        version.add("Jelly Bean")
        version.add("Ice Cream Sandwich")
        version.add("Honeycomb")
        version.add("Gingerbread")
        version.add("Froyo")
        version.add("Eclair")
        version.add("Donut")

        Initialize()
    }

    private fun Initialize() {

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        editTextversion = findViewById(R.id.editTextversion) as EditText

        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        adapter= CustomAdapter(version)
        recyclerView?.adapter = adapter


        editTextversion?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input



                filter(editable.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filterdNames = ArrayList<String>()

        for (s in version) {
            if (s.toLowerCase().contains(text.toLowerCase())) {
                filterdNames.add(s)
            }
        }
        adapter?.filterList(filterdNames)
    }
}


