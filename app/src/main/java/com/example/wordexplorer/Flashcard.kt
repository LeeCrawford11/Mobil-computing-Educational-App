package com.example.wordexplorer
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


enum class Difficulty {
    EASY, MEDIUM, HARD
}
@Entity(tableName = "flashcard_table")
data class Flashcard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val meaning: String,
    val pronunciation: String,
    val exampleSentence: String,
    var isLearned: Boolean = false,
    @ColumnInfo(name = "difficulty") val difficulty: Difficulty
)
