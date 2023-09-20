package com.example.wordexplorer

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class UserStatisticsActivity : AppCompatActivity() {
    private lateinit var viewModel: UserStatisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_statistics_screen)

        // Initialize the ViewModel
        val flashcardDao = FlashcardDatabase.getDatabase(this).flashcardDao()
        val viewModelFactory = UserStatisticsViewModelFactory(flashcardDao)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserStatisticsViewModel::class.java]

        // Set up observers for the counts of learned easy, medium, and hard flashcards
        viewModel.learnedEasyFlashcardsCount.observe(this, Observer { count ->
            val textView = findViewById<TextView>(R.id.easy_flashcards_count)
            textView.text = "Easy flashcards learned: $count"
        })

        viewModel.learnedMediumFlashcardsCount.observe(this, Observer { count ->
            val textView = findViewById<TextView>(R.id.medium_flashcards_count)
            textView.text = "Medium flashcards learned: $count"
        })

        viewModel.learnedHardFlashcardsCount.observe(this, Observer { count ->
            val textView = findViewById<TextView>(R.id.hard_flashcards_count)
            textView.text = "Hard flashcards learned: $count"
        })

        // Set up the back button to finish the activity when clicked
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}
