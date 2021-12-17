package com.dxshulya.homework2

import androidx.lifecycle.ViewModel

private const val TAG = "MainViewModel"

class MainViewModel: ViewModel() {

    var currentIndex = 0
    var isCheater = false
    val currentQuestion: Question
        get() = questionBank[currentIndex]

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))



    fun moveBack() {
        if (currentIndex <= 0) {
            currentIndex = questionBank.size - 1
        } else {
            currentIndex -= 1
        }
    }

    fun moveNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun score(): Double {
        return (questionBank.count { it.correct } / questionBank.count().toDouble()) * 100
    }

    fun checkAnswer(answer: Boolean): Boolean {
        val correct = currentQuestion.correctAnswer == answer
        if (correct) {
            currentQuestion.answeredCorrectly()
        } else {
            currentQuestion.answeredIncorrectly()
        }
        return correct
    }
}