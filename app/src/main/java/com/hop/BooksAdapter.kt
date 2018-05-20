package com.hop

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView


class BooksAdapter// 1
(private val mContext: Achievements, private val books: Array<Book>?) : BaseAdapter() {

    // 2
    override fun getCount(): Int {
        return books!!.size
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
        val book = books!![position]

        // 2

        var convertView2 = convertView
        if (convertView == null) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
            convertView2 = layoutInflater.inflate(R.layout.linearlayout_book, null)
        }

        // 3
        val imageView = convertView2!!.findViewById(R.id.imageview_cover_art) as ImageView
        val nameTextView = convertView2!!.findViewById(R.id.textview_book_name) as TextView
        val authorTextView = convertView2.findViewById(R.id.textview_book_author) as TextView
        val imageViewFavorite = convertView2.findViewById(R.id.imageview_favorite) as ImageView

        // 4
        imageView.setImageResource(book.imageResource)
        nameTextView.text = mContext!!.getString(book.name)
        authorTextView.text = mContext!!.getString(book.author)
        imageViewFavorite.setImageResource(
                if (book.isFavorite) R.drawable.star_enabled else R.drawable.star_disabled)


        return convertView2
    }

}