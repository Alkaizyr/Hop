package com.hop.brewerydb

import android.app.Application
import com.hop.brewerydb.di.AppComponent
import com.hop.brewerydb.di.DaggerAppComponent


class App : Application() {

  companion object {
    lateinit var instance: App
      private set

    val component: AppComponent by lazy { DaggerAppComponent.builder().build() }
  }

  override fun onCreate() {
    super.onCreate()

    instance = this
    component.inject(this)
  }
}