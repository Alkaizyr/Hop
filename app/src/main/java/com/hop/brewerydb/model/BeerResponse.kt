package com.hop.brewerydb.model

import com.google.gson.annotations.SerializedName

data class BeerResponse(@SerializedName("data") val beers: List<Beer>,
                        val currentPage: Int)
