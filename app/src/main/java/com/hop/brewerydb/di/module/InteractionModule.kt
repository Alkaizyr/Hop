package com.hop.brewerydb.di.module

import com.hop.brewerydb.di.module.NetworkingModule
import com.hop.brewerydb.api.BreweryApiService
import com.hop.brewerydb.interaction.BreweryInteractor
import com.hop.brewerydb.interaction.BreweryInteractorInterface
import dagger.Module
import dagger.Provides


@Module(includes = [(NetworkingModule::class)])
class InteractionModule {

  @Provides
  fun provideBreweryInteractor(apiService: BreweryApiService): BreweryInteractorInterface =
      BreweryInteractor(apiService)
}