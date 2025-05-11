package com.example.newsapp.ui.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.ui.presentation.bookmark.BookmarkScreen
import com.example.newsapp.ui.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.ui.presentation.home.HomeScreen
import com.example.newsapp.ui.presentation.home.HomeViewModel
import com.example.newsapp.ui.presentation.news_navigator.NewsNavigator
import com.example.newsapp.ui.presentation.onboarding.OnBoardingScreen
import com.example.newsapp.ui.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String,

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
                    event = viewModel::onEvent
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
    }
}