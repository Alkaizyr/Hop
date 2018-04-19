package com.hop.brewerydb.ui.feed

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.hop.R
import com.hop.brewerydb.common.*
import com.hop.brewerydb.ui.feed.adapter.BeersAdapter
import com.hop.brewerydb.viewmodel.BeersViewModel
import kotlinx.android.synthetic.main.activity_beers.*

class BeersActivity : AppCompatActivity() {

  private val viewModel by lazy { getViewModel<BeersViewModel>() }
  private val adapter = BeersAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_beers)

    initializeUi()

    viewModel.errorData.subscribe(this, this::setErrorVisibility)
    viewModel.loadingData.subscribe(this, this::showLoading)
    viewModel.pageData.subscribe(this, adapter::clearIfNeeded)
    viewModel.beerData.subscribe(this, adapter::addItems)

    viewModel.getBeers(1, "IPA") // request the data for the first time

    pullToRefresh.setOnRefreshListener(viewModel::onRefresh)
  }

  private fun initializeUi() {
    beersList.layoutManager = GridLayoutManager(this, 2)
    beersList.itemAnimator = DefaultItemAnimator()
    beersList.adapter = adapter
  }

  private fun showLoading(isLoading: Boolean) {
    pullToRefresh.isRefreshing = isLoading
  }

  private fun setErrorVisibility(shouldShow: Boolean) {
    errorView.visibility = if (shouldShow) View.VISIBLE else View.GONE
    beersList.visibility = if (!shouldShow) View.VISIBLE else View.GONE
  }
}
