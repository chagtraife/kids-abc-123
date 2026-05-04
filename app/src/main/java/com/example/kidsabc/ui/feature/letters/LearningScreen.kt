package com.example.kidsabc.ui.feature.letters

import android.speech.tts.TextToSpeech
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LearningScreen(
    uppercaseItems: List<LetterItem>,
    lowercaseItems: List<LetterItem>,
    onNavigateToQuiz: () -> Unit,
    onNavigateBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    var selectedItem by remember { mutableStateOf<LetterItem?>(null) }
    var isUppercase by remember { mutableStateOf(true) }
    val items = if (isUppercase) uppercaseItems else lowercaseItems

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

    val chunkSize = (items.size + 2) / 3
    val pages = remember(items) { items.chunked(chunkSize) }
    val pagerState = rememberPagerState(pageCount = { pages.size })

    LaunchedEffect(isUppercase) {
        pagerState.scrollToPage(0)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        PolkaDotBackground()

        Row(modifier = Modifier.fillMaxSize()) {
            // Left half: letter grid with pager
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                // Case toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    listOf(true to "Hoa", false to "Thường").forEach { (upper, label) ->
                        val active = isUppercase == upper
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    if (active) Color.White
                                    else Color.White.copy(alpha = 0.25f)
                                )
                                .pointerInput(upper, isUppercase) {
                                    detectTapGestures(onTap = {
                                        if (!active) {
                                            isUppercase = upper
                                            selectedItem = null
                                        }
                                    })
                                }
                                .padding(horizontal = 20.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (active) Color(0xFF5E35B1) else Color.White
                            )
                        }
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { pageIndex ->
                    val pageItems = pages[pageIndex]
                    val startIndex = pageIndex * chunkSize
                    val rows = pageItems.chunked(5)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        rows.forEachIndexed { rowIndex, rowItems ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                rowItems.forEachIndexed { colIndex, item ->
                                    BubbleLetter(
                                        letter = item.letter,
                                        color = bubbleColors[(startIndex + rowIndex * 5 + colIndex) % bubbleColors.size],
                                        isSelected = item == selectedItem,
                                        modifier = Modifier.weight(1f),
                                        onClick = {
                                            selectedItem = item
                                            tts?.speak(item.pronunciation, TextToSpeech.QUEUE_FLUSH, null, null)
                                        }
                                    )
                                }
                                repeat(5 - rowItems.size) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pages.size) { index ->
                        val selected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(if (selected) 14.dp else 9.dp)
                                .clip(CircleShape)
                                .background(
                                    if (selected) Color.White
                                    else Color.White.copy(alpha = 0.45f)
                                )
                        )
                    }
                }
            }

            // Right half: detail panel
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                val current = selectedItem
                if (current != null) {
                    DetailPanel(
                        item = current,
                        onTap = {
                            val wordVi = current.word.substringBefore(" (").substringAfter("- ").trim()
                            tts?.speak(wordVi, TextToSpeech.QUEUE_FLUSH, null, null)
                        }
                    )
                } else {
                    EmptyDetailPanel()
                }
            }
        }
    }
}

@Composable
private fun DetailPanel(item: LetterItem, onTap: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White.copy(alpha = 0.88f))
                .pointerInput(item) { detectTapGestures(onTap = { onTap() }) },
            contentAlignment = Alignment.Center
        ) {
            val caption = item.word
                .substringBefore(" (")
                .substringAfter("- ")
                .trim()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = item.emoji,
                    fontSize = 80.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = caption,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5E35B1),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun EmptyDetailPanel() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White.copy(alpha = 0.25f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "👆", fontSize = 48.sp)
                Text(
                    text = "Chọn một chữ cái",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
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
private fun BubbleLetter(
    letter: String,
    color: Color,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
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
        modifier = modifier
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
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.35f))
            )
        }
        Text(
            text = letter,
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                drawStyle = Stroke(width = 16f, join = StrokeJoin.Round),
                textAlign = TextAlign.Center
            )
        )
        Text(
            text = letter,
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = color,
                textAlign = TextAlign.Center
            )
        )
    }
}
