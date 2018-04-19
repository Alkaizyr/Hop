package com.hop.brewerydb.common

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity

//A generic function that will just make our code a bit nicer when requesting viewModels
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(): T =
    ViewModelProviders.of(this)[T::class.java]

//This function takes in a lambda, so we don't have to create an observer every time at call site
//crossinline is a modifier that works the same as inline, but it doesn't allow non local returns
inline fun <T> LiveData<T>.subscribe(lifecycle: LifecycleOwner, crossinline onChanged: (T) -> Unit) {
  observe(lifecycle, Observer { it?.run(onChanged) })
}