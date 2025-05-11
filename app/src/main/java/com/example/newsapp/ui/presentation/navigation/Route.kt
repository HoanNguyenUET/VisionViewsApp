package com.example.newsapp.ui.presentation.navigation

sealed class Route(
    val route: String
) {
    object OnBoardingScreen: Route(route = "onboarding screen")
    object HomeScreen: Route(route = "home screen")
    object BookmarkScreen: Route(route = "bookmark screen")
    object DetailsScreen: Route(route = "details screen")
    object AppStartNavigation: Route(route = "app start navigation")
    object NewsNavigation: Route(route = "news navigation")
    object NewsNavigatorScreen: Route(route = "news navigation screen")
    object BlankScreen: Route(route = "blank screen")
}