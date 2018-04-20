package com.hop

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_beer.*

class AddBeerActivity : AppCompatActivity() {
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_beer)

        this.title = "New beer"

        try {
            val bundle: Bundle = intent.extras
            id = bundle.getInt("MainActId", 0)
            if (id != 0) {
                edtName.setText(bundle.getString("MainActName"))
                edtDate.setText(bundle.getString("MainActDate"))
                edtStyle.setText(bundle.getString("MainActStyle"))
                edtBrewery.setText(bundle.getString("MainActBrewery"))
            }
        } catch (ex: Exception) {
        }

        btAdd.setOnClickListener {
            val dbManager = DBManager(this)

            val values = ContentValues()
            values.put("Name", edtName.text.toString())
            values.put("Date", edtDate.text.toString())
            values.put("Style", edtStyle.text.toString())
            values.put("Brewery", edtBrewery.text.toString())

            if (id == 0) {
                val mID = dbManager.insert(values)

                if (mID > 0) {
                    Toast.makeText(this, "Beer added successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed to add beer!", Toast.LENGTH_LONG).show()
                }
            } else {
                val selectionArs = arrayOf(id.toString())
                val mID = dbManager.update(values, "Id=?", selectionArs)

                if (mID > 0) {
                    Toast.makeText(this, "Beer added successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed to add beer!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}