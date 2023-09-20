package com.example.wordexplorer

import org.junit.Test
import org.junit.Assert.*

class FlashcardTest {
    @Test
    fun flashcard_isCreatedCorrectly() {
        val flashcard = Flashcard(
            id = 1,
            word = "Test",
            meaning = "This is a test",
            pronunciation = "Test",
            exampleSentence = "This is a test sentence",
            isLearned = false,
            difficulty = Difficulty.EASY
        )

        assertEquals(1, flashcard.id)
        assertEquals("Test", flashcard.word)
        assertEquals("This is a test", flashcard.meaning)
        assertEquals("Test", flashcard.pronunciation)
        assertEquals("This is a test sentence", flashcard.exampleSentence)
        assertFalse(flashcard.isLearned)
        assertEquals(Difficulty.EASY, flashcard.difficulty)
    }
}
