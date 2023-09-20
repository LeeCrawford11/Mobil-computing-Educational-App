package com.example.wordexplorer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Flashcard::class], version = 5, exportSchema = false)
abstract class FlashcardDatabase : RoomDatabase() {
    // Abstract function returning the DAO for the database
    abstract fun flashcardDao(): FlashcardDao
    // Define a companion object to add functions to the FlashcardDatabase
    companion object {
        @Volatile
        private var INSTANCE: FlashcardDatabase? = null

        // Get the database. Create it if it doesn't exist.
        fun getDatabase(context: Context): FlashcardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashcardDatabase::class.java,
                    "flashcard_database"
                )
                    // Wipes and rebuilds instead of migrating
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
