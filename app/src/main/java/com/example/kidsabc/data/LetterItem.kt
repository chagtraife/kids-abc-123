package com.example.kidsabc.data

data class LetterItem(
    val id: Int,
    val letter: String,
    val word: String,
    val audioResId: Int = 0  // Placeholder for audio resources
)
