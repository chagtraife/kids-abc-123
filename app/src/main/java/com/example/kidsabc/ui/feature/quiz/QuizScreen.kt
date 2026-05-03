package com.example.kidsabc.ui.feature.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kidsabc.data.LetterItem
import kotlin.random.Random

@Composable
fun QuizScreen(
    items: List<LetterItem>,
    onNavigateBack: () -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var feedback by remember { mutableStateOf<Boolean?>(null) }
    var options by remember { mutableStateOf(generateOptions(items, currentIndex)) }

    val currentItem = items[currentIndex]
    val correctAnswer = currentItem.letter

    fun checkAnswer(answer: String) {
        selectedAnswer = answer
        feedback = answer == correctAnswer
    }

    fun nextQuestion() {
        if (currentIndex < items.size - 1) {
            currentIndex++
            selectedAnswer = null
            feedback = null
            options = generateOptions(items, currentIndex)
        } else {
            // Quiz complete
            onNavigateBack()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9C4))  // Light yellow for kids
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Question
            Text(
                text = "Đâu là chữ ${currentItem.letter}?",
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 64.dp)
            )

            // Options
            options.forEach { option ->
                val isSelected = selectedAnswer == option
                val isCorrect = option == correctAnswer

                val backgroundColor = when {
                    isSelected && feedback == true -> Color(0xFF4CAF50)  // Green for correct
                    isSelected && feedback == false -> Color(0xFFf44336)  // Red for wrong
                    feedback == false && isCorrect -> Color(0xFF4CAF50)  // Show correct answer
                    else -> Color.White
                }

                val textColor = when {
                    isSelected && feedback != null -> Color.White
                    else -> Color.Black
                }

                Button(
                    onClick = { if (selectedAnswer == null) checkAnswer(option) },
                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .size(height = 80.dp, width = 200.dp)
                ) {
                    Text(
                        text = option,
                        style = TextStyle(
                            fontSize = 56.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    )
                }
            }

            // Feedback
            if (feedback != null) {
                Text(
                    text = if (feedback == true) "🎉 Tuyệt vời!" else "❌ Thử lại!",
                    style = TextStyle(fontSize = 40.sp),
                    modifier = Modifier.padding(top = 32.dp)
                )

                Button(
                    onClick = { nextQuestion() },
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text("Tiếp tục", fontSize = 24.sp)
                }
            }

            // Back Button
            Button(
                onClick = onNavigateBack,
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Text("Quay lại", fontSize = 20.sp)
            }
        }
    }
}

private fun generateOptions(items: List<LetterItem>, currentIndex: Int): List<String> {
    val correct = items[currentIndex].letter
    val others = items.filterIndexed { index, _ -> index != currentIndex }
        .shuffled()
        .take(2)
        .map { it.letter }

    return (listOf(correct) + others).shuffled()
}
