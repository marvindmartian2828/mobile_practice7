package com.example.mathgameapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random
import android.util.Log

class GameViewModel : ViewModel() {
    private var currentQuestionIndex by mutableStateOf(0)
    var correctAnswers by mutableStateOf(0)
    var wrongAnswers by mutableStateOf(0)

    private val _questions = MutableStateFlow<List<Pair<Int, Int>>>(emptyList())
    val questions = _questions.asStateFlow()

    fun generateQuestions(count: Int) {
        Log.d("GameViewModel", "Generating $count questions")

        if (count <= 0) return // ✅ Prevent invalid question counts

        correctAnswers = 0
        wrongAnswers = 0
        currentQuestionIndex = 0
        _questions.value = List(count) { Random.nextInt(1, 20) to Random.nextInt(1, 20) }

        Log.d("GameViewModel", "Generated Questions: ${_questions.value}")
    }

    fun checkAnswer(answer: Int?) {
        if (questions.value.isNotEmpty() && currentQuestionIndex < questions.value.size) {
            val (a, b) = questions.value[currentQuestionIndex]
            if (answer == a + b) {
                correctAnswers++
                Log.d("GameViewModel", "✅ Correct! $a + $b = $answer")
            } else {
                wrongAnswers++
                Log.d("GameViewModel", "❌ Wrong! $a + $b ≠ $answer")
            }
            currentQuestionIndex++
        }

        if (currentQuestionIndex >= questions.value.size) {
            Log.d("GameViewModel", "🎉 Game Over! Correct: $correctAnswers, Wrong: $wrongAnswers")
        }
    }
}
