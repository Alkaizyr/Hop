package com.hop

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBManager(context: Context) {
    private val dbName = "JSABeers"
    private val dbTable = "Beers"
    private val colId = "Id"
    private val colBeerName = "Name"
    private val colCreationDate = "Date"
    private val colBeerIBU = "IBU"
    private val colBeerABV = "ABV"
    private val colBeerStyle = "Style"
    private val colBrewery = "Brewery"
    private val colBeerDescription = "Description"
    private val colImage = "Image"
    private val dbVersion = 1

    private val createTableSql = "CREATE TABLE IF NOT EXISTS $dbTable ($colId INTEGER PRIMARY KEY,$colBeerName TEXT, $colCreationDate TEXT, $colBeerIBU TEXT, $colBeerABV TEXT, $colBeerStyle TEXT, $colBrewery TEXT, $colBeerDescription TEXT, $colImage BLOB);"
    private var db: SQLiteDatabase? = null

    fun insert(values: ContentValues): Long {
        return db!!.insert(dbTable, "", values)
    }

    fun queryAll(): Cursor {
        return db!!.rawQuery("select * from $dbTable", null)
    }

    fun delete(selection: String, selectionArgs: Array<String>): Int {
        return db!!.delete(dbTable, selection, selectionArgs)
    }

    fun update(values: ContentValues, selection: String, selectionargs: Array<String>): Int {
        return db!!.update(dbTable, values, selection, selectionargs)
    }

    inner class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
        private var context: Context? = context

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(createTableSql)
            Toast.makeText(this.context, " database is created", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS $dbTable")
        }
    }

    init {
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
    }
}
