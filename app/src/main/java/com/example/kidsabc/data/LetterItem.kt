package com.example.kidsabc.data

data class LetterItem(
    val id: Int,
    val letter: String,
    val word: String,
    val pronunciation: String = letter,
    val emoji: String = "❓",
    val audioResId: Int = 0
)
