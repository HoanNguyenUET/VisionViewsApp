package com.example.newsapp.ui.presentation.home

data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)