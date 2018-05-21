package com.hop

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide.init
import java.util.*


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
        if (item.isAchieved) {
            imageView.setImageResource(getOpposite(item.imageResource))
            nameTextView.setTextColor(Color.BLACK)
        }
            else nameTextView.setTextColor(Color.parseColor("#a8a8a8"))


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

}