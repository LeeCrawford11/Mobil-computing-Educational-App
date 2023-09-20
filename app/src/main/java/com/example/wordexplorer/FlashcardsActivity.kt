package com.example.wordexplorer

import FlashcardViewModel
import android.content.Context
import com.example.wordexplorer.FlashcardViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer


class FlashcardsActivity : AppCompatActivity() {
    private lateinit var viewModel: FlashcardViewModel
    private lateinit var adapter: FlashcardAdapter  // Keep a reference to the adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_cards)
        // Initialize the DAO, repository, and ViewModelFactory
        val flashcardDao = FlashcardDatabase.getDatabase(this).flashcardDao()
        val repository = FlashcardRepository(flashcardDao)
        val viewModelFactory = FlashcardViewModelFactory(repository, this)
        // Get a reference to the ViewModel
        viewModel = ViewModelProvider(this, viewModelFactory).get(FlashcardViewModel::class.java)
        // Set up the back button
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        // Set up the RecyclerView and the adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        adapter = FlashcardAdapter(this, viewModel)
        recyclerView.adapter = adapter
        viewModel.flashcards.observe(this, Observer { flashcards ->
            flashcards?.let { adapter.setFlashcards(it) }
        })

    }
    // Clean up when the activity is destroyed
    override fun onDestroy() {
        super.onDestroy()
        adapter.cleanup()
    }
}