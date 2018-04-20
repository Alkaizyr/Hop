package com.hop.brewerydb.di

import com.hop.brewerydb.App
import com.hop.brewerydb.di.module.InteractionModule
import com.hop.brewerydb.interaction.BreweryInteractorInterface
import dagger.Component

@Component(modules = [(InteractionModule::class)])
@ApplicationScope
interface AppComponent {
  fun inject(app: App)

  fun breweryInteractor(): BreweryInteractorInterface
}