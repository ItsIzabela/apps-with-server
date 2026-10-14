package com.example.mobile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    // Adres Django dla emulatora Androida.
    private val apiUrl = "http://127.0.0.1:8000/api/health/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val statusText = findViewById<TextView>(R.id.statusText)
        val checkServerButton =
            findViewById<Button>(R.id.checkServerButton)

        checkServerButton.setOnClickListener {
            statusText.text = "Łączenie z serwerem..."

            Thread {
                try {
                    // Używamy zdefiniowanej wcześniej zmiennej apiUrl
                    val url = java.net.URL(apiUrl)
                    val connection = url.openConnection() as java.net.HttpURLConnection

                    connection.requestMethod = "GET"
                    connection.connectTimeout = 5000
                    connection.readTimeout = 5000

                    val code = connection.responseCode
                    val body = connection.inputStream.bufferedReader().use { it.readText() }

                    runOnUiThread {
                        statusText.text = "HTTP $code\n$body"
                    }

                    connection.disconnect()
                } catch (e: Exception) {
                    android.util.Log.e("API_TEST", "Błąd połączenia z Django", e)

                    runOnUiThread {
                        statusText.text = "${e.javaClass.simpleName}: ${e.message}"
                    }
                }
            }.start()
        }
    }
}