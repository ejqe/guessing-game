package com.erickeugenio.guessinggame

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

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
    var livesLeft = 5

    init {
        secretWordDisplay = deriveSecretWordDisplay()
    }

    fun deriveSecretWordDisplay(): String {
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
        if (isWon()) message = "You win! "
        else if (isLost()) message = "You lost! "

        message += "The word was $secretWord."
        return message
    }






}