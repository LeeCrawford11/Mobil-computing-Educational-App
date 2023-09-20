package com.example.wordexplorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ActivityScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen)

        // Find the flashcards button and set an onClickListener to start FlashcardsActivity when clicked
        val flashcardsButton: ImageButton = findViewById(R.id.flashcardsButton)
        flashcardsButton.setOnClickListener {
            val intent = Intent(this, FlashcardsActivity::class.java)
            startActivity(intent)
        }

        // Find the back button and set an onClickListener to finish the activity when clicked
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}
