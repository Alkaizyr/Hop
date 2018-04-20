package com.hop.brewerydb.api

import com.hop.brewerydb.model.BeerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BreweryApiService {
  @GET("/v2/search")
  fun getBeers(@Query("p") page: Int, @Query("key") apiKey: String, @Query("q") q: String, @Query("type") type: String = "beer"): Call<BeerResponse>
}