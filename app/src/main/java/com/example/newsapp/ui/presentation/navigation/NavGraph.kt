package com.example.newsapp.ui.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.ui.presentation.bookmark.BookmarkScreen
import com.example.newsapp.ui.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.ui.presentation.home.HomeScreen
import com.example.newsapp.ui.presentation.home.HomeViewModel
import com.example.newsapp.ui.presentation.news_navigator.NewsNavigator
import com.example.newsapp.ui.presentation.onboarding.OnBoardingScreen
import com.example.newsapp.ui.presentation.onboarding.OnBoardingViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    startDestination: String,
    openAppSettings: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                // View Model
                val viewModel: OnBoardingViewModel = viewModel(
                    factory = OnBoardingViewModel.Factory
                )

                // Screen
                OnBoardingScreen(
                    event = viewModel::onEvent,
                    openAppSettings = openAppSettings
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route){
                NewsNavigator()
            }
        }

        navigation(
            route = Route.BlankScreenNavigation.route,
            startDestination = Route.BlankScreen.route
        ) {
            composable(route = Route.BlankScreen.route) {
                BlankScreen()
            }
        }
    }
}

@Composable
fun BlankScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "NEW\nTALKING",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = colorResource(R.color.blue),
            textAlign = TextAlign.Center
        )
    }
}