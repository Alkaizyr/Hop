package com.hop

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import java.util.*
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import java.time.LocalDateTime
import kotlin.math.floor


class AchievementAdapter// 1
(private val mContext: Achievements, private val items: Array<GridItem>) : BaseAdapter() {

    // 2
    override fun getCount(): Int {
        return items.size
    }

    // 3
    override fun getItemId(position: Int): Long {
        return 0
    }

    // 4
    override fun getItem(position: Int): Any? {
        return null
    }

    // 5
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // 1
        val item = items[position]

        // 2

        var convertView2 = convertView
        if (convertView == null) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
            convertView2 = layoutInflater.inflate(R.layout.grid_item, null)
        }

        // 3
        val imageView = convertView2!!.findViewById(R.id.imageview_cover_art) as ImageView
        val nameTextView = convertView2.findViewById(R.id.textview_book_name) as TextView
        //val authorTextView = convertView2.findViewById(R.id.textview_book_author) as TextView
        val imageViewFavorite = convertView2.findViewById(R.id.imageview_achieved) as ImageView

        // 4
        imageView.setImageResource(item.imageResource)

        nameTextView.text = mContext.getString(item.name)
        //authorTextView.text = mContext!!.getString(b.author)

        if (!item.isAchieved) item.isAchieved = getAchieveState(item.name)

        if (item.isAchieved) {
            imageView.setImageResource(getOpposite(item.imageResource))
            nameTextView.setTextColor(Color.BLACK)
        }
            else nameTextView.setTextColor(Color.parseColor("#a3a3a3"))


        return convertView2
    }

    val imageOpposites: HashMap<Int,Int> = HashMap()


    init {
        imageOpposites[R.drawable.ic_reward_brewery_g] = R.drawable.ic_reward_brewery_c
        imageOpposites[R.drawable.ic_reward_beer_g] = R.drawable.ic_reward_beer_c
        imageOpposites[R.drawable.ic_reward_country_g] = R.drawable.ic_reward_country_c
        imageOpposites[R.drawable.ic_reward_calendar_g] = R.drawable.ic_reward_calendar_c
        imageOpposites[R.drawable.ic_reward_country_g] = R.drawable.ic_reward_country_c
        imageOpposites[R.drawable.ic_reward_style_g] = R.drawable.ic_reward_style_c
    }


    fun getOppositeNullable(imgRes: Int): Int? {

        return imageOpposites.get(imgRes)

    }

    fun getOpposite(img: Int): Int {

        if (getOppositeNullable(img) == null) return R.drawable.ic_reward_brewery_g
        return getOppositeNullable(img)!!

    }

    fun getAchieveState(name: Int): Boolean {
        when (name) {
            R.string.first_beer -> if (mContext.beerCount != 0) return true
            R.string.one_month -> {
                val pm = mContext.getPackageManager()
                val packageInfo: PackageInfo = pm.getPackageInfo(mContext.packageName, 0)

                val time = packageInfo.firstInstallTime
                val totalTime = (System.currentTimeMillis() - time) / 1000 / 60 / 60 / 24
                if (totalTime >= 30) return true
            }
            R.string.ten_beers -> if (mContext.beerCount >= 10) return true
            R.string.five_styles -> if (mContext.stylesCount >= 5) return true
            R.string.ten_breweries -> if (mContext.breweriesCount >= 10) return true
            R.string.three_months -> {
                val pm = mContext.getPackageManager()
                val packageInfo: PackageInfo = pm.getPackageInfo(mContext.packageName, 0)

                val time = packageInfo.firstInstallTime
                val totalTime = (System.currentTimeMillis() - time) / 1000 / 60 / 60 / 24
                if (totalTime >= 60) return true
            }
            R.string.ten_styles -> if (mContext.stylesCount >= 10) return true
            R.string.fifty_beers -> if (mContext.beerCount >= 50) return true
        }
        return false
    }

}