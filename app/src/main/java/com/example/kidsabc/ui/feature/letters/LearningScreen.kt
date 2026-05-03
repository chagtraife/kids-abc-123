package com.example.kidsabc.ui.feature.letters

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kidsabc.data.LetterItem

@Composable
fun LearningScreen(
    items: List<LetterItem>,
    onNavigateToQuiz: () -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "scale"
    )

    val currentItem = items[currentIndex]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount > 50 && currentIndex > 0 -> currentIndex--
                        dragAmount < -50 && currentIndex < items.size - 1 -> currentIndex++
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Large Letter
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = currentItem.letter,
                    style = TextStyle(
                        fontSize = 200.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .graphicsLayer(scaleX = scale, scaleY = scale)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    isPressed = true
                                    tryAwaitRelease()
                                    isPressed = false
                                }
                            )
                        }
                )
            }

            // Word Hint
            Text(
                text = currentItem.word,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(bottom = 48.dp)
            )

            // Navigation Info
            Text(
                text = "← Swipe →",
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Quiz Button
            Button(
                onClick = onNavigateToQuiz,
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text("Quiz", fontSize = 24.sp)
            }
        }
    }
}
