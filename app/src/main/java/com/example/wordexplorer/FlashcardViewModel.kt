import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordexplorer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlashcardViewModel(private val repository: FlashcardRepository, private val context: Context) : ViewModel() {

    lateinit var flashcards: LiveData<List<Flashcard>>

    init {
        initializeDatabase()
        setFlashcardsByDifficulty()
    }
    // Insert a flashcard into the database
    fun insert(flashcard: Flashcard) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(flashcard)
    }
    // Update a flashcard in the database
    fun update(flashcard: Flashcard) = viewModelScope.launch {
        repository.update(flashcard)
    }
    // Update the learned status of a flashcard
    fun updateLearnedStatus(word: String, status: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateLearnedStatus(word, status)
    }
    // Get the count of learned flashcards
    fun getLearnedFlashcardCount() = viewModelScope.launch(Dispatchers.IO) {
        repository.getLearnedFlashcardCount()
    }
    // Set the flashcards by difficulty
    fun setFlashcardsByDifficulty() {
        val sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val difficultyString = sharedPreferences.getString("difficulty", "EASY")
        val difficulty = difficultyString?.let { Difficulty.valueOf(it.uppercase()) } ?: Difficulty.EASY
        flashcards = repository.getFlashcardsByDifficulty(difficulty)
    }
    // Initialize the database by difficulty
    private fun initializeDatabase() = viewModelScope.launch(Dispatchers.IO) {
        if (repository.getFlashcardCount() == 0) {
            easyWords.forEach { insert(it) }
            mediumWords.forEach { insert(it) }
            hardWords.forEach { insert(it) }
        }
    }
}
