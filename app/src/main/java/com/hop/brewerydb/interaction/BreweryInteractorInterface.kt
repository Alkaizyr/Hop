package com.hop.brewerydb.interaction

import com.hop.brewerydb.model.BeerResponse
import retrofit2.Callback

interface BreweryInteractorInterface {

  fun getBeers(page: Int, name: String, callback: Callback<BeerResponse>)
}