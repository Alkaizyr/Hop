package com.hop

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import java.util.ArrayList


class Achievements :  AppCompatActivity() {

    var beerCount = 0

    var stylesCount = 0

    var breweriesCount = 0

    var oldBool = false

    val items : Array<GridItem> = arrayOf(
            GridItem(R.string.first_beer,       R.drawable.ic_reward_beer_g),
            GridItem(R.string.one_month,        R.drawable.ic_reward_calendar_g),
            GridItem(R.string.ten_beers,        R.drawable.ic_reward_beer_g),
            GridItem(R.string.five_styles,      R.drawable.ic_reward_style_g),
            GridItem(R.string.twenty_five_beers,      R.drawable.ic_reward_beer_g),
            GridItem(R.string.ten_breweries,    R.drawable.ic_reward_brewery_g),
            GridItem(R.string.three_months,     R.drawable.ic_reward_calendar_g),
            GridItem(R.string.ten_styles,       R.drawable.ic_reward_style_g),
            GridItem(R.string.fifty_beers,      R.drawable.ic_reward_beer_g),
            GridItem(R.string.ten_years_beer,   R.drawable.ic_reward_gray))

    private val favoritedBookNamesKey = "favoritedBookNamesKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        setTitle("Achievements")

        val gridView = (findViewById<View>(R.id.gridview) as GridView)
        val achievementAdapter = AchievementAdapter(this, items)
        gridView.adapter = achievementAdapter

        gridView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val book: GridItem = items[position]

                // This tells the GridView to redraw itself
                // in turn calling your AchievementAdapter's getView method again for each cell
                //achievementAdapter.notifyDataSetChanged()
            }
        }

        beerCount = intent.extras.getInt("beer_list", 0)
        stylesCount = intent.extras.getInt("numberOfStyles", 0)
        breweriesCount = intent.extras.getInt("numberOfBreweries", 0)
        oldBool = intent.extras.getBoolean("oldbool", false)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val favoritedBookNames = ArrayList<Int>()
        for (book in items) {
            if (book.isAchieved) {
                favoritedBookNames.add(book.name)
            }
        }

        outState.putIntegerArrayList(favoritedBookNamesKey, favoritedBookNames)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val favoritedBookNames = savedInstanceState.getIntegerArrayList(favoritedBookNamesKey)

        for (bookName in favoritedBookNames!!) {
            for (book in items) {
                if (book.name == bookName) {
                    //book.toggleAchieved()
                    break
                }
            }
        }
    }

}
