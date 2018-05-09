package com.hop.brewerydb.model

data class Beer(val name: String = "",
                val description: String = "",
                var ibu: String = "",
                val abv: String = "",
                val style: BeerStyle = BeerStyle(),
                val labels: BeerLabels = BeerLabels())
