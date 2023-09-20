package com.example.wordexplorer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner

class SettingsActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize the spinner and the back button
        spinner = findViewById(R.id.difficultySpinner)
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        // Get the user's saved difficulty setting
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val difficulty = sharedPreferences.getString("difficulty", "EASY")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Difficulty.values())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        // Set the spinner's selected item to the user's saved difficulty setting
        val spinnerPosition = arrayAdapter.getPosition(difficulty?.let { Difficulty.valueOf(it) })
        spinner.setSelection(spinnerPosition)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedDifficulty = parent?.getItemAtPosition(position).toString()
                with(sharedPreferences.edit()) {
                    putString("difficulty", selectedDifficulty)
                    apply()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }
}
