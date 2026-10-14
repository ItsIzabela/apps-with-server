package com.example.mobile

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val apiUrl = "http://127.0.0.1:8000/api/player/"
    private lateinit var headerText: TextView
    private lateinit var playersContainer: LinearLayout

    private val handler = Handler(Looper.getMainLooper())
    private var isPolling = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headerText = findViewById(R.id.headerText)
        playersContainer = findViewById(R.id.playersContainer)

        startPolling()
    }

    private fun startPolling() {
        Thread {
            while (isPolling) {
                try {
                    val url = URL(apiUrl)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.connectTimeout = 3000
                    connection.readTimeout = 3000

                    if (connection.responseCode == 200) {
                        val body = connection.inputStream.bufferedReader().use { it.readText() }
                        val json = JSONObject(body)

                        val count = json.getInt("count")
                        val maxPlayers = json.getInt("max_players")
                        val playersArray = json.getJSONArray("players")

                        runOnUiThread {
                            headerText.text = "Lobby Graczy ($count/$maxPlayers)"
                            playersContainer.removeAllViews() // Czyścimy przed narysowaniem od nowa

                            for (i in 0 until playersArray.length()) {
                                val player = playersArray.getJSONObject(i)
                                val name = player.getString("name")
                                val colorHex = player.getString("color")

                                val playerCard = LinearLayout(this).apply {
                                    orientation = LinearLayout.VERTICAL
                                    gravity = Gravity.CENTER
                                    setPadding(16, 16, 16, 16)
                                }

                                val nameTv = TextView(this).apply {
                                    text = name
                                    gravity = Gravity.CENTER
                                }

                                val square = View(this).apply {
                                    layoutParams = LinearLayout.LayoutParams(100, 100).apply {
                                        topMargin = 10
                                    }
                                    setBackgroundColor(Color.parseColor(colorHex))
                                }

                                playerCard.addView(nameTv)
                                playerCard.addView(square)
                                playersContainer.addView(playerCard)
                            }
                        }
                    }
                    connection.disconnect()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                Thread.sleep(1000)
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        isPolling = false
    }
}