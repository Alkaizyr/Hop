package com.hop

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast


class AchievementAdapter
(private val mContext: Achievements, private val items: Array<GridItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val item = items[position]


        var convertView2 = convertView
        if (convertView == null) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
            convertView2 = layoutInflater.inflate(R.layout.grid_item, null)
        }

        val imageView = convertView2!!.findViewById(R.id.imageview_cover_art) as ImageView
        val nameTextView = convertView2.findViewById(R.id.textview_book_name) as TextView

        imageView.setImageResource(item.imageResOff)

        nameTextView.text = mContext.getString(item.name)

        val sharedPrefer = mContext.getPreferences(Context.MODE_PRIVATE)
        val savedValue: Boolean = sharedPrefer.getBoolean(mContext.getString(item.name), false)
        item.isAchieved = savedValue

        if (!item.isAchieved) {
            item.isAchieved = item.logic()
            if (item.isAchieved) {
                val myToast = Toast.makeText(mContext, "Achievement Unlocked: " + mContext.getString(item.name), Toast.LENGTH_SHORT)
                myToast.show()
                val sharedPref = mContext.getPreferences(Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    putBoolean(mContext.getString(item.name),item.isAchieved)
                    apply()
                }
            }
        }

        if (item.isAchieved) {
            imageView.setImageResource(item.imageResOn)
            nameTextView.setTextColor(Color.BLACK)
        }
            else nameTextView.setTextColor(Color.parseColor("#a3a3a3"))

        return convertView2
    }

}