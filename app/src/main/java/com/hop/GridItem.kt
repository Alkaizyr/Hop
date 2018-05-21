package com.hop

class GridItem(val name: Int, val author: Int, val imageResource: Int) {
    var isAchieved = false

    fun toggleAchieved() {
        isAchieved = !isAchieved
    }
}