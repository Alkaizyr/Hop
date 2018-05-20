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

        val books : Array<Book> = arrayOf(Book(R.string.abc_an_amazing_alphabet_book, R.string.dr_seuss, R.drawable.abc,
                "http://www.raywenderlich.com/wp-content/uploads/2016/03/abc.jpg"),
        Book(R.string.are_you_my_mother, R.string.dr_seuss, R.drawable.areyoumymother,
        "http://www.raywenderlich.com/wp-content/uploads/2016/03/areyoumymother.jpg"),
        Book(R.string.where_is_babys_belly_button, R.string.karen_katz, R.drawable.whereisbabysbellybutton,
        "http://www.raywenderlich.com/wp-content/uploads/2016/03/whereisbabysbellybutton.jpg"),
        Book(R.string.on_the_night_you_were_born, R.string.nancy_tillman, R.drawable.onthenightyouwereborn,
        "http://www.raywenderlich.com/wp-content/uploads/2016/03/onthenightyouwereborn.jpg"),
        Book(R.string.hand_hand_fingers_thumb, R.string.dr_seuss, R.drawable.handhandfingersthumb,
        "http://www.raywenderlich.com/wp-content/uploads/2016/03/handhandfingersthumb.jpg"),
        Book(R.string.the_very_hungry_caterpillar, R.string.eric_carle, R.drawable.theveryhungrycaterpillar,
        "http://www.raywenderlich.com/wp-content/uploads/2016/03/theveryhungrycaterpillar.jpg"),
        Book(R.string.the_going_to_bed_book, R.string.sandra_boynton, R.drawable.thegoingtobedbook,
        "http://www.raywenderlich.com/wp-content/uploads/2016/03/thegoingtobedbook.jpg"),
        Book(R.string.oh_baby_go_baby, R.string.dr_seuss, R.drawable.ohbabygobaby,
        "http://www.raywenderlich.com/wp-content/uploads/2016/03/ohbabygobaby.jpg"),
        Book(R.string.the_tooth_book, R.string.dr_seuss, R.drawable.thetoothbook,
        "http://www.raywenderlich.com/wp-content/uploads/2016/03/thetoothbook.jpg"),
        Book(R.string.one_fish_two_fish_red_fish_blue_fish, R.string.dr_seuss, R.drawable.onefish,
        "http://www.raywenderlich.com/wp-content/uploads/2016/03/onefish.jpg"))

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
