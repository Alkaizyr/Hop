package com.hop

class Book(val name: Int, val author: Int, val imageResource: Int) {
    var isFavorite = false

    fun toggleFavorite() {
        isFavorite = !isFavorite
    }
}