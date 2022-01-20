package com.example.challenge3fragmentsbootcamp.main

import android.util.Log
import com.example.challenge3fragmentsbootcamp.Crypto
import com.example.challenge3fragmentsbootcamp.api.KrJsonResponse
import com.example.challenge3fragmentsbootcamp.api.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository {
    suspend fun fetchCryptos(): MutableList<Crypto> {
        return withContext(Dispatchers.IO) {
            val krJsonResponse = service.getLastHourCryptos()
            val krList = parseCryptoResult(krJsonResponse)

            Log.d("CRYPTO_LIST", krList.toString())

            krList
        }
    }

    private fun parseCryptoResult(krJsonResponse: KrJsonResponse): MutableList<Crypto> {

        val krList = mutableListOf<Crypto>()
        val entriesList = krJsonResponse.entries

        for (access in entriesList){
            val name = access.nombre
            val abrv = access.abreviatura
            val date = access.fecha_creacion
            val price = access.precio

            val img = access.imagen
            val path = img.path

            krList.add(Crypto(name, abrv, img, date, price))
        }


        return  krList

    }
}