package com.example.wordexplorer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserStatisticsViewModel(private val flashcardDao: FlashcardDao) : ViewModel() {
    val learnedEasyFlashcardsCount: LiveData<Int> = flashcardDao.getLearnedFlashcardsCountByDifficulty(Difficulty.EASY)
    val learnedMediumFlashcardsCount: LiveData<Int> = flashcardDao.getLearnedFlashcardsCountByDifficulty(Difficulty.MEDIUM)
    val learnedHardFlashcardsCount: LiveData<Int> = flashcardDao.getLearnedFlashcardsCountByDifficulty(Difficulty.HARD)
}
