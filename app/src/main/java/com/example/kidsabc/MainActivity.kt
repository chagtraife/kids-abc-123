package com.example.kidsabc

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kidsabc.data.SampleData
import com.example.kidsabc.ui.feature.letters.LearningScreen
import com.example.kidsabc.ui.feature.quiz.QuizScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide system UI for fullscreen experience
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )

        setContent {
            KidsABCApp()
        }
    }
}

@Composable
fun KidsABCApp() {
    val navController = rememberNavController()
    var isLetters by remember { mutableStateOf(true) }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AppContent(
                navController = navController,
                isLetters = isLetters,
                onToggleMode = { isLetters = !isLetters }
            )
        }
    }
}

@Composable
private fun AppContent(
    navController: NavHostController,
    isLetters: Boolean,
    onToggleMode: () -> Unit
) {
    val items = if (isLetters) SampleData.letters else SampleData.numbers

    NavHost(navController = navController, startDestination = "learning") {
        composable("learning") {
            LearningScreen(
                items = items,
                onNavigateToQuiz = { navController.navigate("quiz") },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("quiz") {
            QuizScreen(
                items = items,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
