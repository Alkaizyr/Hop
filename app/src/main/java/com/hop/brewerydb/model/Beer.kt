package com.hop.brewerydb.model

data class Beer(val name: String = "",
                val style: BeerStyle = BeerStyle(),
                val labels: BeerLabels = BeerLabels())