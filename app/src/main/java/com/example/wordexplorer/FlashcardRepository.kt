package com.example.wordexplorer

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData

class FlashcardRepository(private val flashcardDao: FlashcardDao) {
    // Create LiveData to notify when the data has changed
    val allFlashcards: LiveData<List<Flashcard>> = flashcardDao.getAllFlashcards().asLiveData()

    suspend fun insert(flashcard: Flashcard) {
        flashcardDao.insert(flashcard)
    }

    suspend fun update(flashcard: Flashcard) {
        flashcardDao.update(flashcard)
    }
    // retrieve the number of flashcards in the database.
    suspend fun getFlashcardCount(): Int {
        return flashcardDao.getFlashcardCount()
    }
    // update the learned status of a flashcard
    suspend fun updateLearnedStatus(word: String, status: Boolean) {
        flashcardDao.updateLearnedStatus(word, status)
    }
    // retrieve the number of learned flashcards in the database
    suspend fun getLearnedFlashcardCount(): Int {
        return flashcardDao.getLearnedFlashcardCount()
    }
    // retrieve all flashcards of a certain difficulty
    fun getFlashcardsByDifficulty(difficulty: Difficulty): LiveData<List<Flashcard>> {
        return flashcardDao.getFlashcardsByDifficulty(difficulty)
    }
}
