package com.hop

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_achievements.*


class Achievements :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        setTitle("Achievements")

        val books : Array<Book> = arrayOf(Book(R.string.First_Beer, R.string.empty_string, R.drawable.ic_reward_beer_c),
        Book(R.string.one_month, R.string.empty_string, R.drawable.ic_reward_calendar_c),
        Book(R.string.ten_beers, R.string.empty_string, R.drawable.ic_reward_beer_c),
        Book(R.string.ten_countries, R.string.empty_string, R.drawable.ic_reward_country_c),
        Book(R.string.five_styles, R.string.empty_string, R.drawable.ic_reward_style_c),
        Book(R.string.ten_breweries, R.string.empty_string, R.drawable.ic_reward_brewery_c),
        Book(R.string.three_months, R.string.empty_string, R.drawable.ic_reward_calendar_g),
        Book(R.string.ten_styles, R.string.empty_string, R.drawable.ic_reward_style_g),
        Book(R.string.twenty_countries, R.string.empty_string, R.drawable.ic_reward_country_g),
        Book(R.string.fifty_beers, R.string.empty_string, R.drawable.ic_reward_beer_g))

        //val gridView = gridview
        val gridView = (findViewById<View>(R.id.gridview) as GridView)
        val booksAdapter = BooksAdapter(this, books)
        gridView.adapter = booksAdapter

        gridView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val book: Book = books[position]
                book.toggleFavorite()

                // This tells the GridView to redraw itself
                // in turn calling your BooksAdapter's getView method again for each cell
                booksAdapter.notifyDataSetChanged()
            }
        }




    }
}
