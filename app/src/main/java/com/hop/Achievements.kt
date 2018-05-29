package com.hop

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.support.v7.app.AppCompatActivity
import android.widget.GridView


class Achievements :  AppCompatActivity() {

    var beerCount = 0

    var stylesCount = 0

    var breweriesCount = 0

    var oldBool = false

    var fields_Filled = false

    val items : Array<GridItem> = arrayOf(
            GridItem(R.string.first_beer,       R.drawable.ic_reward_beer_g),
            GridItem(R.string.one_month,        R.drawable.ic_reward_calendar_g),
            GridItem(R.string.ten_beers,        R.drawable.ic_reward_beer_g),
            GridItem(R.string.five_styles,      R.drawable.ic_reward_style_g),
            GridItem(R.string.twenty_five_beers,      R.drawable.ic_reward_beer_g),
            GridItem(R.string.ten_breweries,    R.drawable.ic_reward_brewery_g),
            GridItem(R.string.all_fields_filled,     R.drawable.ic_item_settings_bw),
            GridItem(R.string.three_months,     R.drawable.ic_reward_calendar_g),
            GridItem(R.string.ten_styles,       R.drawable.ic_reward_style_g),
            GridItem(R.string.fifty_beers,      R.drawable.ic_reward_beer_g),
            GridItem(R.string.ten_years_beer,   R.drawable.ic_reward_gray),
            GridItem(R.string.twenty_five_breweries,    R.drawable.ic_reward_brewery_g),
            GridItem(R.string.one_year,     R.drawable.ic_reward_calendar_g))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        setTitle("Achievements")

        val gridView = (findViewById<View>(R.id.gridview) as GridView)
        val achievementAdapter = AchievementAdapter(this, items)
        gridView.adapter = achievementAdapter

        gridView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
        }

        beerCount = intent.extras.getInt("beer_list", 0)
        stylesCount = intent.extras.getInt("numberOfStyles", 0)
        breweriesCount = intent.extras.getInt("numberOfBreweries", 0)
        oldBool = intent.extras.getBoolean("oldbool", false)
        fields_Filled = intent.extras.getBoolean("fieldsFilled", false)

    }

}
