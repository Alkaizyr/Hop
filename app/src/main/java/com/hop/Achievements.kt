package com.hop

import android.app.Dialog
import android.content.pm.PackageInfo
import android.os.Bundle
import android.view.View
import android.support.v7.app.AppCompatActivity
import android.widget.*


class Achievements :  AppCompatActivity() {

    private var beerCount = 0

    private var stylesCount = 0

    private var breweriesCount = 0

    private var oldBool = false

    private var fieldsFilled = false

    private var totalDays = -1

    private val items : Array<GridItem> = arrayOf(
            GridItem(R.string.first_beer, R.drawable.ic_reward_beer_g, R.drawable.ic_reward_beer_c, {beerCount != 0}),
            GridItem(R.string.one_month, R.drawable.ic_reward_calendar_g, R.drawable.ic_reward_calendar_c, {totalDays >= 30}),
            GridItem(R.string.ten_beers, R.drawable.ic_reward_beer_g, R.drawable.ic_reward_beer_c, {beerCount >= 10}),
            GridItem(R.string.five_styles, R.drawable.ic_reward_style_g, R.drawable.ic_reward_style_c, {stylesCount >= 5}),
            GridItem(R.string.twenty_five_beers, R.drawable.ic_reward_beer_g, R.drawable.ic_reward_beer_c, {beerCount >= 25}),
            GridItem(R.string.ten_breweries, R.drawable.ic_reward_brewery_g, R.drawable.ic_reward_brewery_c, {breweriesCount >= 10}),
            GridItem(R.string.all_fields_filled, R.drawable.ic_item_settings_bw, R.drawable.ic_item_settings, {fieldsFilled}),
            GridItem(R.string.three_months, R.drawable.ic_reward_calendar_g, R.drawable.ic_reward_calendar_c, {totalDays >= 60}),
            GridItem(R.string.ten_styles, R.drawable.ic_reward_style_g, R.drawable.ic_reward_style_c, {stylesCount >= 10}),
            GridItem(R.string.fifty_beers, R.drawable.ic_reward_beer_g, R.drawable.ic_reward_beer_c, {beerCount >= 50}),
            GridItem(R.string.ten_years_beer, R.drawable.ic_reward_gray, R.drawable.ic_reward_orange, {oldBool}),
            GridItem(R.string.twenty_five_breweries, R.drawable.ic_reward_brewery_g, R.drawable.ic_reward_brewery_c, {breweriesCount >= 25}),
            GridItem(R.string.one_year, R.drawable.ic_reward_calendar_g, R.drawable.ic_reward_calendar_c, {totalDays >= 365})
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        title = "Achievements"

        val gridView = (findViewById<View>(R.id.gridview) as GridView)
        val achievementAdapter = AchievementAdapter(this, items)
        gridView.adapter = achievementAdapter

        beerCount = intent.extras.getInt("beer_list", 0)
        stylesCount = intent.extras.getInt("numberOfStyles", 0)
        breweriesCount = intent.extras.getInt("numberOfBreweries", 0)
        oldBool = intent.extras.getBoolean("oldbool", false)
        fieldsFilled = intent.extras.getBoolean("fieldsFilled", false)

        val pm = packageManager
        val packageInfo: PackageInfo = pm.getPackageInfo(packageName, 0)
        val time = packageInfo.firstInstallTime
        totalDays = ((System.currentTimeMillis() - time) / 1000 / 60 / 60 / 24).toInt()

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val dialog = Dialog(this@Achievements)
            dialog.setContentView(R.layout.dialog_achievement)

            dialog.create()

            val title = dialog.findViewById<View>(R.id.dialog_achievement_title) as TextView
            val descr = dialog.findViewById<View>(R.id.dialog_achievement_description) as TextView

            title.text = getString(items[position].name)
            descr.text = description(position)

            val dialogLayout = dialog.findViewById<View>(R.id.dialog_achievement_ok) as Button
            dialogLayout.setOnClickListener {
                dialog.cancel()
            }

            dialog.show()
        }

    }

    fun description(position: Int): String {
        return details(position) + conclusion(position)
    }

    fun details(position: Int): String {
        val det = "Details: "
        return when (position) {
            1,7,12 -> det + "Age of your collection  -  " + getString(items[position].name) + getString(R.string.enter)

            6 -> det + "Beer with all the fields filled in in your collection" + getString(R.string.enter)

            else -> det + getString(items[position].name) + " in your collection" + getString(R.string.enter)
        }
    }

    private fun conclusion(position: Int): String {
        if (items[position].isAchieved) return getString(R.string.congrat)
        return when (position) {
            1,7,12 -> "You need a little more time to earn this achievement:)"
            10 -> "You need an old enough beer to earn this achievement:)"
            6 -> "You need to add a beer with all the fields filled in to earn this achievement:)"
            else -> "You need a few more beers to earn this achievement:)"
        }
    }

}
