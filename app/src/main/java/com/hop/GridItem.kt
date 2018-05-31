package com.hop

class GridItem(val name: Int, val imageResOff: Int, val imageResOn: Int, logicIn: () -> (Boolean)) {
    var isAchieved = false
    val logic = logicIn

}