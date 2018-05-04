package com.hop.brewerydb.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hop.brewerydb.App
import com.hop.brewerydb.model.Beer
import com.hop.brewerydb.model.BeerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeersViewModel : ViewModel() {
    private val interactor by lazy { App.component.breweryInteractor() }

    val errorData = MutableLiveData<Boolean>()
    val loadingData = MutableLiveData<Boolean>()
    val pageData = MutableLiveData<Int>()
    val beerData = MutableLiveData<List<Beer>>()

    fun getBeers(page: Int, name: String) {
        interactor.getBeers(page, name, beersCallback())
    }

    fun onRefresh() {
        pageData.value = 1
    }

    private fun beersCallback() = object : Callback<BeerResponse> {
        override fun onFailure(call: Call<BeerResponse>?, t: Throwable?) {
            loadingData.value = false
            errorData.value = true
        }

        override fun onResponse(call: Call<BeerResponse>?, response: Response<BeerResponse>?) {
            loadingData.value = false //data has come, stop the loading
            errorData.value = false

            response?.body()?.run {
                updateData(this)
            }
        }

        private fun updateData(data: BeerResponse) {
            pageData.value = data.currentPage + 1 // increment the page
            beerData.value = data.beers
        }
    }
}
