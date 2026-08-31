package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.BurhanViewModel
import com.example.data.ScreenDestination
import com.example.ui.screens.*
import com.example.ui.theme.AlBurhanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlBurhanTheme {
                // Force Right-To-Left (RTL) for pure Arabic native experience
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AlBurhanApp()
                    }
                }
            }
        }
    }
}

@Composable
fun AlBurhanApp(
    viewModel: BurhanViewModel = viewModel()
) {
    val currentScreen by viewModel.currentScreen.collectAsState()

    when (val screen = currentScreen) {
        is ScreenDestination.Home -> {
            HomeScreen(viewModel = viewModel)
        }
        is ScreenDestination.TrainingSelection -> {
            TrainingSelectionScreen(branch = screen.branch, viewModel = viewModel)
        }
        is ScreenDestination.Quiz -> {
            QuizExamScreen(viewModel = viewModel)
        }
        is ScreenDestination.Result -> {
            ExamResultScreen(result = screen.result, viewModel = viewModel)
        }
        is ScreenDestination.Dashboard -> {
            DashboardScreen(viewModel = viewModel)
        }
        is ScreenDestination.Badges -> {
            BadgesScreen(viewModel = viewModel)
        }
    }
}
