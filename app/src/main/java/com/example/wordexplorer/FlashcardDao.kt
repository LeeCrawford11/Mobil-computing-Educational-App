package com.example.wordexplorer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface FlashcardDao {
    @Query("SELECT * from flashcard_table ORDER BY word ASC")
    fun getAllFlashcards(): Flow<List<Flashcard>>

    @Insert
    fun insert(flashcard: Flashcard)

    @Update
    fun update(flashcard: Flashcard)

    @Query("DELETE FROM flashcard_table")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM flashcard_table")
    fun getFlashcardCount(): Int

    @Query("UPDATE flashcard_table SET isLearned = :status WHERE word = :word")
    fun updateLearnedStatus(word: String, status: Boolean)

    @Query("SELECT COUNT(*) FROM flashcard_table WHERE isLearned = 1")
    fun getLearnedFlashcardCount(): Int

    @Query("SELECT * FROM flashcard_table WHERE difficulty = :difficulty")
    fun getFlashcardsByDifficulty(difficulty: Difficulty): LiveData<List<Flashcard>>

    @Query("SELECT COUNT(*) FROM flashcard_table WHERE isLearned = 1 AND difficulty = :difficulty")
    fun getLearnedFlashcardsCountByDifficulty(difficulty: Difficulty): LiveData<Int>
}
