package com.hop

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import java.util.ArrayList


class Achievements :  AppCompatActivity() {

    val items : Array<GridItem> = arrayOf(
            GridItem(R.string.First_Beer,       R.string.empty_string, R.drawable.ic_reward_beer_g),
            GridItem(R.string.one_month,        R.string.empty_string, R.drawable.ic_reward_calendar_g),
            GridItem(R.string.ten_beers,        R.string.empty_string, R.drawable.ic_reward_beer_g),
            GridItem(R.string.ten_countries,    R.string.empty_string, R.drawable.ic_reward_country_g),
            GridItem(R.string.five_styles,      R.string.empty_string, R.drawable.ic_reward_style_g),
            GridItem(R.string.ten_breweries,    R.string.empty_string, R.drawable.ic_reward_brewery_g),
            GridItem(R.string.three_months,     R.string.empty_string, R.drawable.ic_reward_calendar_g),
            GridItem(R.string.ten_styles,       R.string.empty_string, R.drawable.ic_reward_style_g),
            GridItem(R.string.twenty_countries, R.string.empty_string, R.drawable.ic_reward_country_g),
            GridItem(R.string.fifty_beers,      R.string.empty_string, R.drawable.ic_reward_beer_g))

    private val favoritedBookNamesKey = "favoritedBookNamesKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        setTitle("Achievements")

        val gridView = (findViewById<View>(R.id.gridview) as GridView)
        val booksAdapter = AchievementAdapter(this, items)
        gridView.adapter = booksAdapter

        gridView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val book: GridItem = items[position]
                book.toggleAchieved()

                // This tells the GridView to redraw itself
                // in turn calling your AchievementAdapter's getView method again for each cell
                booksAdapter.notifyDataSetChanged()
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // construct a list of books you've favorited
        val favoritedBookNames = ArrayList<Int>()
        for (book in items) {
            if (book.isAchieved) {
                favoritedBookNames.add(book.name)
            }
        }

        // save that list to outState for later
        outState.putIntegerArrayList(favoritedBookNamesKey, favoritedBookNames)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // get our previously saved list of favorited books
        val favoritedBookNames = savedInstanceState.getIntegerArrayList(favoritedBookNamesKey)

        // look at all of your books and figure out which are the favorites
        for (bookName in favoritedBookNames!!) {
            for (book in items) {
                if (book.name == bookName) {
                    book.toggleAchieved()
                    break
                }
            }
        }
    }

}
