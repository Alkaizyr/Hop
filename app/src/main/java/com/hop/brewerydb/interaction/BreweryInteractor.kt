package com.hop.brewerydb.interaction

import com.hop.brewerydb.api.BreweryApiService
import com.hop.brewerydb.common.API_KEY
import com.hop.brewerydb.model.BeerResponse
import retrofit2.Callback

class BreweryInteractor(private val apiService: BreweryApiService) : BreweryInteractorInterface {

  override fun getBeers(page: Int, name: String, callback: Callback<BeerResponse>) {
    apiService.getBeers(page, API_KEY, name).enqueue(callback)
  }
}