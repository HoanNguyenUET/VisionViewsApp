package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.ui.presentation.navigation.NavGraph
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                NewsApp()
            }
        }
    }
}

@Composable
fun NewsApp(
    viewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
) {
    NavGraph(
        startDestination = viewModel.startDestination.value
    )
}