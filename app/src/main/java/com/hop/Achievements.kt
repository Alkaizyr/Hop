package com.hop

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AlertDialog
import android.widget.*


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
                val ratingdialog = Dialog(this@Achievements)
                ratingdialog.setContentView(R.layout.dialog_achievement)

                ratingdialog.create()

                val title = ratingdialog.findViewById<View>(R.id.dialog_achievement_title) as TextView
                val descr = ratingdialog.findViewById<View>(R.id.dialog_achievement_description) as TextView

                title.text = getString(items[position].name)
                descr.text = description(position)

                val dialog_layout = ratingdialog.findViewById<View>(R.id.dialog_achievement_ok) as Button
                dialog_layout.setOnClickListener {
                    ratingdialog.cancel()
                }

                ratingdialog.show()
            }
        }

        beerCount = intent.extras.getInt("beer_list", 0)
        stylesCount = intent.extras.getInt("numberOfStyles", 0)
        breweriesCount = intent.extras.getInt("numberOfBreweries", 0)
        oldBool = intent.extras.getBoolean("oldbool", false)
        fields_Filled = intent.extras.getBoolean("fieldsFilled", false)

        /*var c = 0
        for (item in items){
            if (item.isAchieved) c++
        }
        val number = "(" + c + "/" + items.size + ")" //"(0/10)"*/


    }

    fun description(position: Int): String {
        return details(position) + conclusion(position)
    }

    fun details(position: Int): String {
        val det = "Details: "
        when (position) {
            1,7,12 -> return det + "Age of your collection  -  " + getString(items[position].name) + getString(R.string.enter)

            6 -> return det + "Beer with all the fields filled in in your collection" + getString(R.string.enter)

            else -> return det + getString(items[position].name) + " in your collection" + getString(R.string.enter)
        }
    }

    fun conclusion(position: Int): String {
        if (items[position].isAchieved) return getString(R.string.congrat)
        when (position) {
            1,7,12 -> return "You need a little more time to earn this achievement:)"
            10 -> return "You need an old enough beer to earn this achievement:)"
            6 -> return "You need to add a beer with all the fields filled in to earn this achievement:)"
            else -> {
                if (items[position].isAchieved) return getString(R.string.congrat)
                else return "You need a few more beers to earn this achievement:)"
            }

        }
    }

}
