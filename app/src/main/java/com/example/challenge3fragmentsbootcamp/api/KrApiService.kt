package com.example.challenge3fragmentsbootcamp.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface EqApiService {
    @GET("MD_BC2201_Cryptos")
    suspend fun getLastHourCryptos(): KrJsonResponse
}

private var retrofit = Retrofit.Builder()
    .baseUrl("https://o-informatica.com/cms/api/collections/get/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

var service: EqApiService = retrofit.create(EqApiService::class.java)