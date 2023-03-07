package com.erickeugenio.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erickeugenio.guessinggame.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    val words = listOf(
        "Android",
        "Fragment",
        "Activity",
        "Binding",
        "View",
        "Model",
        "Factory"
    )

    val secretWord = words.random().uppercase()
    var secretWordDisplay = ""
    var correctGuesses = ""
    var incorrectGuesses = ""
    var livesLeft = 10


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root

        secretWordDisplay = deriveSecretWordDisplay()
        updateScreen()

        return view



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun updateScreen() {
        binding.word.text = secretWordDisplay
        binding.lives.text = "You have $livesLeft lives left"
        binding.incorrectGuesses.text = "Incorrect guesses: $incorrectGuesses"
    }

    fun deriveSecretWordDisplay() : String  {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }

        return display
    }

    fun checkLetter(str: String) = when (correctGuesses.contains(str)) {
        true -> str
        false -> "_"
    }

    fun makeGuess(guess: String) {
        if (guess.length == 1) {
            if (secretWord.contains(guess)) {
                correctGuesses += guess
                secretWordDisplay = deriveSecretWordDisplay()
            } else {
                incorrectGuesses += "$guess "
                livesLeft--
            }
        }
    }

    fun isWon() = secretWord.equals(secretWordDisplay, true)

    fun isLost() = livesLeft <= 0

    fun wonLostMessage(): String {
        var message = ""
        if (isWon()) message = "You win!"
        else if (isLost()) message = "You lost!"

        message += "The word was $secretWord."
        return message

    }














}