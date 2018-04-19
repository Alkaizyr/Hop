package com.hop.brewerydb.di.module

import com.hop.brewerydb.api.BreweryApiService
import com.hop.brewerydb.common.BASE_URL
import com.hop.brewerydb.di.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@ApplicationScope
class NetworkingModule {

  @Provides
  fun provideBaseUrl(): String = BASE_URL

  @Provides
  fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }

  @Provides
  fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
      OkHttpClient.Builder()
          .addInterceptor(loggingInterceptor)
          .build()

  @Provides
  fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

  @Provides
  fun provideRetrofitClient(client: OkHttpClient,
                            baseUrl: String,
                            converter: GsonConverterFactory): Retrofit =
      Retrofit.Builder()
          .client(client)
          .addConverterFactory(converter)
          .baseUrl(baseUrl)
          .build()

  @Provides
  fun provideBreweryApiService(retrofit: Retrofit): BreweryApiService =
      retrofit.create(BreweryApiService::class.java)
}