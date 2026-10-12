package com.example.mobile.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Twój adres serwera (dla emulatora to zazwyczaj 10.0.2.2)
    private const val BASE_URL = "http://10.0.2.2:4000/"

    // Inicjalizacja Retrofita
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
