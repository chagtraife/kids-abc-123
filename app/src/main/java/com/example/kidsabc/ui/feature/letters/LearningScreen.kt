package com.example.kidsabc.ui.feature.letters

import android.speech.tts.TextToSpeech
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kidsabc.data.LetterItem
import java.util.Locale

private val bubbleColors = listOf(
    Color(0xFFE53935), Color(0xFFFF7043), Color(0xFF7B1FA2), Color(0xFF00ACC1),
    Color(0xFFD81B60), Color(0xFFE53935), Color(0xFFFF7043), Color(0xFF00BCD4),
    Color(0xFFEC407A), Color(0xFFFFD600), Color(0xFFFF8C00), Color(0xFFEC407A),
    Color(0xFF43A047), Color(0xFF1565C0), Color(0xFFAB47BC), Color(0xFF00ACC1),
    Color(0xFFFFD600), Color(0xFF43A047), Color(0xFF00ACC1), Color(0xFF5E35B1),
    Color(0xFFFFD600), Color(0xFFFF7043), Color(0xFFEC407A), Color(0xFFFF7043),
    Color(0xFFFF7043), Color(0xFF00ACC1), Color(0xFF5E35B1), Color(0xFFEC407A),
    Color(0xFF1A237E), Color(0xFF43A047)
)

@Composable
fun LearningScreen(
    items: List<LetterItem>,
    onNavigateToQuiz: () -> Unit,
    onNavigateBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }

    DisposableEffect(Unit) {
        var instance: TextToSpeech? = null
        instance = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                instance?.setLanguage(Locale("vi", "VN"))
                tts = instance
            }
        }
        onDispose {
            instance?.shutdown()
            tts = null
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        PolkaDotBackground()

        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(
                onBack = onNavigateBack,
                onReadAll = {
                    items.forEachIndexed { i, item ->
                        val mode = if (i == 0) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD
                        tts?.speak(item.letter, mode, null, "letter_$i")
                    }
                },
                onSing = {},
                onInfo = onNavigateToQuiz
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(8),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(items) { index, item ->
                    BubbleLetter(
                        letter = item.letter,
                        color = bubbleColors[index % bubbleColors.size],
                        onClick = {
                            tts?.speak(item.letter, TextToSpeech.QUEUE_FLUSH, null, null)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun PolkaDotBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(color = Color(0xFFBB78D8))
        val dotColor = Color(0xFFC98FDF)
        val dotRadius = 22.dp.toPx()
        val spacingX = 90.dp.toPx()
        val spacingY = 78.dp.toPx()
        var row = 0
        var y = -dotRadius
        while (y < size.height + dotRadius) {
            val xOffset = if (row % 2 == 0) 0f else spacingX / 2
            var x = xOffset - spacingX
            while (x < size.width + dotRadius) {
                drawCircle(color = dotColor, radius = dotRadius, center = Offset(x, y))
                x += spacingX
            }
            y += spacingY
            row++
        }
    }
}

@Composable
private fun TopBar(
    onBack: () -> Unit,
    onReadAll: () -> Unit,
    onSing: () -> Unit,
    onInfo: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleButton(color = Color(0xFFE53935), onClick = onBack) {
            Text("◀", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            CircleButton(color = Color(0xFF66BB6A), onClick = onReadAll) {
                Text("Đọc", fontSize = 15.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
            CircleButton(color = Color(0xFFE91E63), onClick = onSing) {
                Text("Hát", fontSize = 15.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
        CircleButton(color = Color(0xFF7B1FA2), onClick = onInfo) {
            Text("i", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun BubbleLetter(letter: String, color: Color, onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.75f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    pressed = true
                    onClick()
                    tryAwaitRelease()
                    pressed = false
                })
            },
        contentAlignment = Alignment.Center
    ) {
        // White outline for bubble effect
        Text(
            text = letter,
            style = TextStyle(
                fontSize = 52.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                drawStyle = Stroke(width = 20f, join = StrokeJoin.Round),
                textAlign = TextAlign.Center
            )
        )
        // Colored fill
        Text(
            text = letter,
            style = TextStyle(
                fontSize = 52.sp,
                fontWeight = FontWeight.ExtraBold,
                color = color,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
private fun CircleButton(
    color: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(color)
            .pointerInput(Unit) { detectTapGestures(onTap = { onClick() }) },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
