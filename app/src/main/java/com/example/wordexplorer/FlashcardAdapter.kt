package com.example.wordexplorer

import FlashcardViewModel
import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageButton
import java.util.*

class FlashcardAdapter(private val context: Context, private val viewModel: FlashcardViewModel) : RecyclerView.Adapter<FlashcardAdapter.ViewHolder>() {

    // Variable to store the flashcards
    private var flashcards = emptyList<Flashcard>()
    // TextToSpeech variable to enable flashcards to be read out loud
    private var tts: TextToSpeech? = null
    init {

        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts!!.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported")
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardFront: TextView = itemView.findViewById(R.id.card_front) // get reference to front of card
        val cardBack: TextView = itemView.findViewById(R.id.card_back) // get reference to back of card
        val speakButton: ImageButton = itemView.findViewById(R.id.speak_button) // get reference to speak button
        val flipButton: ImageButton = itemView.findViewById(R.id.flip_button)  // get reference to flip button
        val learnedCheckbox: CheckBox = itemView.findViewById(R.id.learned_checkbox) // get reference to learned checkbox


        init {
            // Flip the card when the card is clicked
            itemView.setOnClickListener {
                flipCard(cardFront, cardBack)
            }
            // Use TextToSpeech to read out the front of the card when the speak button is clicked
            speakButton.setOnClickListener {
                val text = cardFront.text.toString()
                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
            }
            // Flip the card when the flip button is clicked
            flipButton.setOnClickListener {
                flipCard(cardFront, cardBack)  // set click listener to flip the card
            }
            // Set click listener for the learned checkbox to update the learned status in the database
            learnedCheckbox.setOnClickListener {
                val flashcard = flashcards[adapterPosition]
                flashcard.isLearned = (it as CheckBox).isChecked
                // update in database
                viewModel.updateLearnedStatus(flashcard.word, flashcard.isLearned)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.flashcard_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = flashcards[position]
        holder.cardFront.text = current.word
        holder.cardBack.text = "${current.meaning}\n\n${current.pronunciation}\n\nExample:\n${current.exampleSentence}"
        holder.learnedCheckbox.isChecked = current.isLearned
    }
    // Return the number of flashcards
    override fun getItemCount() = flashcards.size

    // Update the list of flashcards and tell the adapter that the data has changed
    internal fun setFlashcards(flashcards: List<Flashcard>) {
        this.flashcards = flashcards
        notifyDataSetChanged()
    }
    // Method to flip a card, showing either the front or the back
    private fun flipCard(front: View, back: View) {
        if (front.visibility == View.VISIBLE) {
            // If front is visible, animate it to rotate and then show the back
            val frontAnim = ObjectAnimator.ofFloat(front, "rotationY", 0f, 180f)
            frontAnim.duration = 800

            val backAnim = ObjectAnimator.ofFloat(back, "rotationY", -180f, 0f)
            backAnim.duration = 800

            frontAnim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    front.visibility = View.GONE
                    back.visibility = View.VISIBLE
                    backAnim.start()
                }
                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
            frontAnim.start()
        } else {
            // If back is visible, animate it to rotate and then show the front
            val frontAnim = ObjectAnimator.ofFloat(back, "rotationY", 0f, 180f)
            frontAnim.duration = 800

            val backAnim = ObjectAnimator.ofFloat(front, "rotationY", -180f, 0f)
            backAnim.duration = 800

            frontAnim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    back.visibility = View.GONE
                    front.visibility = View.VISIBLE
                    backAnim.start()
                }
                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
            frontAnim.start()
        }
    }

    fun cleanup() {
        tts?.stop()
        tts?.shutdown()
    }
}
