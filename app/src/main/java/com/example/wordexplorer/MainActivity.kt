package com.example.wordexplorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the start button to transition to ActivityScreen
        val startButton: ImageButton = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            val intent = Intent(this, ActivityScreen::class.java)
            startActivity(intent)
        }

        // Set up the settings button to transition to SettingsActivity
        val settingsButton: ImageButton = findViewById(R.id.settingsButton)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Set up the user settings button to transition to UserStatisticsActivity
        val userSettingsButton: ImageButton = findViewById(R.id.userStatisticsButton)
        userSettingsButton.setOnClickListener {
            val intent = Intent(this, UserStatisticsActivity::class.java)
            startActivity(intent)
        }
    }
}