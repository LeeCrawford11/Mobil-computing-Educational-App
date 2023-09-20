package com.example.wordexplorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserStatisticsViewModelFactory(
    private val flashcardDao: FlashcardDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the ViewModel class is UserStatisticsViewModel
        if (modelClass.isAssignableFrom(UserStatisticsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserStatisticsViewModel(flashcardDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}