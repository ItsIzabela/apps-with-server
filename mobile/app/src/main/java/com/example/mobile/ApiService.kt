package com.example.mobile.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // Tutaj definiujesz endpointy (ścieżki serwera)
    @GET("api/mobile-data")
    fun getMobileData(): Call<MobileDataResponse>
}
