package com.example.mobile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.network.MobileDataResponse
import com.example.mobile.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        // Wywołanie API przez Retrofit
        RetrofitClient.instance.getMobileData().enqueue(object : Callback<MobileDataResponse> {
            override fun onResponse(call: Call<MobileDataResponse>, response: Response<MobileDataResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.message ?: "Brak danych"
                    textView.text = message
                } else {
                    textView.text = "Błąd odpowiedzi: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<MobileDataResponse>, t: Throwable) {
                textView.text = "Błąd połączenia: ${t.message}"
            }
        })
    }
}
